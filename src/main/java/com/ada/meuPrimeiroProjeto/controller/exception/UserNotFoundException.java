package com.ada.meuPrimeiroProjeto.controller.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message) {
    super(message);
  }
}
