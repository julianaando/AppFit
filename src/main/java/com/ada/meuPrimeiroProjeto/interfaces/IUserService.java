package com.ada.meuPrimeiroProjeto.interfaces;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import java.util.List;

public interface IUserService {

  List<UserResponse> getUsers();

  UserResponse saveUser(UserRequest userDTO);

  UserResponse getUserById(Integer id);

  UserResponse getUserByEmail(String email);

  List<UserResponse> getAllByName(String name);

  void deleteUser(Integer id);

  UserResponse updateUser(Integer id, UserRequest userRequest);
}
