package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import java.util.ArrayList;
import java.util.List;

public class ActvConvert {

  public static Activities toEntity(User user, List<Exercise> exercises) {
    Activities actv = new Activities();
    actv.setUser(user);
    actv.setExercises(exercises);

    return actv;
  }

  public static ActvResponse toResponse(Activities actv) {
    ActvResponse actvResponse = new ActvResponse();
    actvResponse.setId(actv.getId());
    actvResponse.setUser(actv.getUser());
    actvResponse.setExercises(actv.getExercises());

    return actvResponse;
  }

  public static List<ActvResponse> toResponseList(List<Activities> activities) {
    List<ActvResponse> actvResponse = new ArrayList<>();
    for (Activities actv : activities) {
      actvResponse.add(toResponse(actv));
    }

    return actvResponse;
  }

}
