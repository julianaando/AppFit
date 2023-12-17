package com.ada.meuPrimeiroProjeto.controller.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExerciseRequest {
  @NotBlank()
  private String name;
  private Integer typeId;
}
