package com.ada.meuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExRequest {
  private String name;
  private LocalDate date;
  private Integer typeId;
}
