package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.LoginRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
    var auth = new UsernamePasswordAuthenticationToken(
        loginRequest.getEmail(),
        loginRequest.getPassword()
    );

    String message;

    try {
      authenticationManager.authenticate(auth);
      message = "Seu login foi realizado com sucesso!";
    } catch (AuthenticationException e) {
      message = "Usuário ou senha incorretos";
    }

    LoginResponse response = new LoginResponse(message);
    return ResponseEntity.ok(response);
  }
}
