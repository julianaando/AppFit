package com.ada.meuPrimeiroProjeto.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivitiesRequest {
  private Integer userId;
  private List<Integer> exercisesIds;
}
