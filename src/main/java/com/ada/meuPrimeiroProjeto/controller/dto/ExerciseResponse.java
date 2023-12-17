package com.ada.meuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExerciseResponse {
  private Integer id;
  private String name;
  private TypeExerciseResponse type;
}
