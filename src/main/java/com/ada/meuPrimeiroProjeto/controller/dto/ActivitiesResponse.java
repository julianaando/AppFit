package com.ada.meuPrimeiroProjeto.controller.dto;

import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActivitiesResponse {
  private Integer id;
  private User user;
  private List<Exercise> exercises;
  private LocalDate date;
}
