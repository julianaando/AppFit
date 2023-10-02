package com.ada.MeuPrimeiroProjeto.service;

import com.ada.MeuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.MeuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.MeuPrimeiroProjeto.utils.UserConvert;
import com.ada.MeuPrimeiroProjeto.utils.Validator;
import java.util.List;
import com.ada.MeuPrimeiroProjeto.model.User;
import com.ada.MeuPrimeiroProjeto.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public Page<UserResponse> getUsers(int page, int size, String direction){
    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "name");
    Page<User> users = userRepository.findAll(pageRequest);
    return UserConvert.toResponsePage(users);
  }

  public UserResponse saveUser(UserRequest userDTO) throws PasswordValidationError {
    User user = UserConvert.toEntity(userDTO);
    user.setActive(true);
    if(!Validator.passwordValidate(user.getPassword())) throw new PasswordValidationError("Senha fora do padrão");
    User userEntity = userRepository.save(user);
    return UserConvert.toResponse(userEntity);
  }

  public UserResponse getUserById(Integer id){
    Optional<User> userResponse =  userRepository.findById(id);
    if(userResponse.isPresent()){
      return UserConvert.toResponse(userResponse.get());
    } else {
      throw new RuntimeException("Usuário não encontrado!");
    }
  }

  public UserResponse getUserByEmail(String email){
    Optional<User> userResponse =  userRepository.findByEmail(email);
    if(!Validator.emailValidate(email)) throw new RuntimeException("Email fora do padrão");
    if(userResponse.isEmpty()) {
      throw new RuntimeException("Email não encontrado!");
    }
    return UserConvert.toResponse(userResponse.get());

  }

  public List<UserResponse> getAllByName(String name){
    List<User> userResponse =  userRepository.findAllByName(name);
    if(userResponse.isEmpty()){
      throw new RuntimeException("Nome não encontrado!");
    }
    return UserConvert.toResponseList(userRepository.findAllByName(name));
  }

  public void deleteUser(Integer id){
    User user = userRepository.findById(id).orElseThrow();
    user.setActive(false);
    userRepository.save(user);
  }

  public UserResponse updateUser(Integer id, UserRequest userRequest){
    User user = UserConvert.toEntity(userRequest);
    user.setId(id);
    User userEntity = userRepository.save(user);
    return UserConvert.toResponse(userEntity);
  }

}
