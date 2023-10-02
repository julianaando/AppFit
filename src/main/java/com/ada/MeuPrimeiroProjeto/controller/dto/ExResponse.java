package com.ada.MeuPrimeiroProjeto.controller.dto;

import com.ada.MeuPrimeiroProjeto.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExResponse {
  private Integer id;
  private String name;
  private Double weight;
  private String reps;
  private TypeExResponse type;
}
