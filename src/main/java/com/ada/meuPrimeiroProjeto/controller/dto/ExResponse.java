package com.ada.meuPrimeiroProjeto.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExResponse {
  private Integer id;
  private String name;
  private String date;
  private TypeExResponse type;
}
