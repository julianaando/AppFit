package com.ada.meuPrimeiroProjeto.controller.exception;

public class EmailNotFoundException extends RuntimeException{

  public EmailNotFoundException(String message) {
    super(message);
  }
}
