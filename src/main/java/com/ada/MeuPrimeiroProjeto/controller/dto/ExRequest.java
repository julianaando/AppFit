package com.ada.MeuPrimeiroProjeto.controller.dto;

import com.ada.MeuPrimeiroProjeto.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExRequest {
  private User userid;
  private String name;
  private String reps;
  private Double weight;
  private Integer sets;
  private Integer rest;
}
