package com.ada.meuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ExerciseRequest {
  private String name;
  private LocalDate date;
  private Integer typeId;
}
