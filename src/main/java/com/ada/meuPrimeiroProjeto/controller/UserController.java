package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ada.meuPrimeiroProjeto.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @RequestMapping
  public ResponseEntity<Page<UserResponse>>getUsers(
      @RequestParam(
          value = "page",
          required = false,
          defaultValue = "0"
      ) int page,
      @RequestParam(
          value = "size",
          required = false,
          defaultValue = "10"
      ) int size,
      @RequestParam(
          value = "direction",
          required = false,
          defaultValue = "ASC"
      ) String direction
  ){
    return ResponseEntity.ok(userService.getUsers(page, size, direction));
  }

  @PostMapping
  public ResponseEntity<UserResponse> saveUser(
      @Valid @RequestBody UserRequest userDTO
  ) throws PasswordValidationError {
    UserResponse user =  userService.saveUser(userDTO);
    return ResponseEntity.created(URI.create("/user/"+user.getId())).body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable Integer id){
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<UserResponse>> getAllByName(@PathVariable String name){
    return ResponseEntity.ok(userService.getAllByName(name));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id){
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable Integer id,
      @RequestBody UserRequest userRequest){
    return ResponseEntity.ok(userService.updateUser(id, userRequest));
  }
}
