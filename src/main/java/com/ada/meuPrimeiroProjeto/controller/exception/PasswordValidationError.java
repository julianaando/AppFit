package com.ada.meuPrimeiroProjeto.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordValidationError extends Exception {
    private String description;
}
