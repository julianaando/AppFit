package com.ada.meuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExerciseRequest {
  private String name;
  private Integer typeId;
}
