package com.ada.meuPrimeiroProjeto.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordValidationError extends Exception {
    private String description;
}
