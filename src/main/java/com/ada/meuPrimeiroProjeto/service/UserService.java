package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.meuPrimeiroProjeto.utils.UserConvert;
import com.ada.meuPrimeiroProjeto.utils.Validator;
import java.util.List;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public Page<UserResponse> getUsers(int page, int size, String direction){
    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "name");
    Page<User> users = userRepository.findAll(pageRequest);
    return UserConvert.toResponsePage(users);
  }

  public UserResponse saveUser(UserRequest userDTO) throws PasswordValidationError {
    User user = UserConvert.toEntity(userDTO);

    String encodePassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodePassword);

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
    return UserConvert.toResponse(userRepository.findByEmail(email));

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
