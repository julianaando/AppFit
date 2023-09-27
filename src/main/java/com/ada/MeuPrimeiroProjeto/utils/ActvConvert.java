package com.ada.MeuPrimeiroProjeto.utils;

import com.ada.MeuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.MeuPrimeiroProjeto.model.Activities;
import com.ada.MeuPrimeiroProjeto.model.User;
import java.util.ArrayList;
import java.util.List;

public class ActvConvert {

  public static Activities toEntity(ActvRequest actvDTO, User user) {
    Activities actv = new Activities();
    actv.setUser(user);
    actv.setExercise(actvDTO.getExercise());
    actv.setDate(actvDTO.getDate());


    return actv;
  }

  public static ActvResponse toResponse(Activities actv) {
    ActvResponse actvResponse = new ActvResponse();
    actvResponse.setId(actv.getId());
    actvResponse.setUser(actv.getUser());
    actvResponse.setSets(actv.getSets());
    actvResponse.setWeight(actv.getWeight());
    actvResponse.setReps(actv.getReps());
    actvResponse.setRest(actv.getRest());

    return actvResponse;
  }

  public static List<ActvResponse> toResponseList(List<Activities> actvs) {
    List<ActvResponse> actvResponse = new ArrayList<>();
    for (Activities actv : actvs) {
      actvResponse.add(toResponse(actv));
    }

    return actvResponse;
  }

}
