package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.controller.exception.EmailNotFoundException;
import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.meuPrimeiroProjeto.controller.exception.ValidationError;
import com.ada.meuPrimeiroProjeto.interfaces.IUserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ada.meuPrimeiroProjeto.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  private final IUserService userService;

  @Autowired
  public UserController(IUserService userService){
    this.userService = userService;
  }

  @RequestMapping
  public ResponseEntity<List<UserResponse>> getUsers(){
    return ResponseEntity.ok(userService.getUsers());
  }

  @PostMapping
  public ResponseEntity<?>saveUser(
    @Valid @RequestBody UserRequest userRequest
  ) {
    try {
      UserResponse user = userService.saveUser(userRequest);
      return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    } catch (PasswordValidationError e) {
      return ResponseEntity.badRequest().body(new ValidationError("password", e.getMessage()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable Integer id){
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable String email){
    try {
      return ResponseEntity.ok(userService.getUserByEmail(email));
    } catch (EmailNotFoundException e) {
      return ResponseEntity.badRequest().body(new ValidationError("email", e.getMessage()));
    }
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
