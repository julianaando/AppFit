package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.LoginRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TokenResponse;
import com.ada.meuPrimeiroProjeto.controller.infra.security.TokenService;
import com.ada.meuPrimeiroProjeto.model.User;
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

  @Autowired
  TokenService tokenService;

  @PostMapping
  public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
    var auth = new UsernamePasswordAuthenticationToken(
        loginRequest.getEmail(),
        loginRequest.getPassword()
    );

    try {
      var authentication = authenticationManager.authenticate(auth);
      var token = tokenService.tokenGenerate((User) authentication.getPrincipal());
      return ResponseEntity.ok().body(new TokenResponse(token));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(401).body("Usuário ou senha incorretos");
    }
  }
}
