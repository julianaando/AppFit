package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesResponse;
import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import java.util.ArrayList;
import java.util.List;

public class ActivitiesConvert {

  public static Activities toEntity(User user, List<Exercise> exercises, ActivitiesRequest activitiesRequest) {
    Activities actv = new Activities();
    actv.setUser(user);
    actv.setExercises(exercises);
    actv.setDate(activitiesRequest.getDate());

    return actv;
  }

  public static ActivitiesResponse toResponse(Activities actv) {
    ActivitiesResponse activitiesResponse = new ActivitiesResponse();
    activitiesResponse.setId(actv.getId());
    activitiesResponse.setUser(actv.getUser());
    activitiesResponse.setExercises(actv.getExercises());
    activitiesResponse.setDate(actv.getDate());

    return activitiesResponse;
  }

  public static List<ActivitiesResponse> toResponseList(List<Activities> activities) {
    List<ActivitiesResponse> activitiesResponse = new ArrayList<>();
    for (Activities actv : activities) {
      activitiesResponse.add(toResponse(actv));
    }

    return activitiesResponse;
  }

}
