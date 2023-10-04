package com.ada.meuPrimeiroProjeto.utils;

public class Validator {
  // pelo menos 1 letra maiúscula, 1 dígito, 1 caractere especial e 8 caracteres
  public static Boolean passwordValidate(String password){
    return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,}$");
  }

  // pelo menos 3 caracteres antes do @, pelo menos 2 caracteres entre o @ e o . e pelo menos 2 caracteres depois do .
  public static Boolean emailValidate(String email){
    return email.matches("^\\S{3,}@\\w{2,}\\.[a-zA-Z]{2,}$");
  }

}
