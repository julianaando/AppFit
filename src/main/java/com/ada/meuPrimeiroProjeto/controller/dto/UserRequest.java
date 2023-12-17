package com.ada.meuPrimeiroProjeto.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
  @NotBlank()
  private String name;
  @Email
  private String email;
  private String password;
}
