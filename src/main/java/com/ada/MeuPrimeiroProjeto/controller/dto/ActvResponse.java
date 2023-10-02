package com.ada.MeuPrimeiroProjeto.controller.dto;

import com.ada.MeuPrimeiroProjeto.model.Exercise;
import com.ada.MeuPrimeiroProjeto.model.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActvResponse {
  private Integer id;
  private User user;
  private List<Exercise> exercises;
}
