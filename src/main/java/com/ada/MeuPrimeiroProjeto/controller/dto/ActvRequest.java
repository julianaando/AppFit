package com.ada.MeuPrimeiroProjeto.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActvRequest {
  private Integer userId;
  private LocalDate date;
  private String exercise;
}
