package com.ada.meuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExRequest {
  private String name;
  private String date;
  private Integer typeId;
}
