package com.ada.MeuPrimeiroProjeto.controller.dto;

import com.ada.MeuPrimeiroProjeto.model.Activities;
import com.ada.MeuPrimeiroProjeto.model.User;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActvResponse {
  private Integer id;
  private User user;
  private Double weight;
  private Integer sets;
  private Integer rest;
  private String reps;
}
