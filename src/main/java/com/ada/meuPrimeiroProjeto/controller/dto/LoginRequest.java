package com.ada.meuPrimeiroProjeto.controller.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {
  @Email
  private String email;
  private String password;
}
