package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class UserConvert {

  public static User toEntity(UserRequest userRequest) {
    User user = new User();
    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPassword(userRequest.getPassword());
    return user;
  }

  public static UserResponse toResponse(User user){
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setName(user.getName());
    userResponse.setEmail(user.getEmail());

    return userResponse;
  }

  public static List<UserResponse> toResponseList(List<User> users){
    List<UserResponse> userResponses = new ArrayList<>();
    for (User user : users) {
      UserResponse userResponse = UserConvert.toResponse(user);
      userResponses.add(userResponse);
    }
    return userResponses;
  }

}
