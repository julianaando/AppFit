package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.interfaces.IUserService;
import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.utils.UserConvert;
import java.util.List;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService implements IUserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

/*  @Autowired
  PasswordEncoder passwordEncoder;*/

  @Override
  public List<UserResponse> getUsers(){
    List<User> userResponse =  userRepository.findAll();
    if(userResponse.isEmpty()){
      throw new RuntimeException("Não há usuários cadastrados!");
    }
    return UserConvert.toResponseList(userRepository.findAll());
  }

  @Override
  public UserResponse saveUser(UserRequest userRequest) {
    User user = UserConvert.toEntity(userRequest);

/*    String encodePassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodePassword);*/

    user.setActive(true);
/*
    if(!Validator.passwordValidate(user.getPassword())) throw new PasswordValidationError("Senha fora do padrão");
*/
    User userEntity = userRepository.save(user);
    return UserConvert.toResponse(userEntity);
  }

  @Override
  public UserResponse getUserById(Integer id){
    Optional<User> userResponse =  userRepository.findById(id);
    if(userResponse.isPresent()){
      return UserConvert.toResponse(userResponse.get());
    } else {
      throw new RuntimeException("Usuário não encontrado!");
    }
  }

  @Override
  public UserResponse getUserByEmail(String email){
    User userResponse =  userRepository.findByEmail(email);
    if(userResponse == null){
      throw new RuntimeException("Email não encontrado!");
    }
    return UserConvert.toResponse(userRepository.findByEmail(email));
  }

  @Override
  public List<UserResponse> getAllByName(String name){
    List<User> userResponse =  userRepository.findAllByName(name);
    if(userResponse.isEmpty()){
      throw new RuntimeException("Nome não encontrado!");
    }
    return UserConvert.toResponseList(userRepository.findAllByName(name));
  }

  @Override
  public void deleteUser(Integer id){
    Optional<User> userResponse =  userRepository.findById(id);
    if(userResponse.isPresent()){
      userResponse.get().setActive(false);
      userRepository.delete(userResponse.get());
    } else {
      throw new RuntimeException("Usuário não encontrado!");
    }
  }

  @Override
  public UserResponse updateUser(Integer id, UserRequest userRequest){
    User user = UserConvert.toEntity(userRequest);
    user.setId(id);
    User userEntity = userRepository.save(user);
    return UserConvert.toResponse(userEntity);
  }

}
