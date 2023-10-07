package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.ExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import java.util.ArrayList;
import java.util.List;

public class ExConvert {

  public static Exercise toEntity(ExRequest exRequest, TypeExercise typeExercise) {
    Exercise exercise = new Exercise();
    exercise.setName(exRequest.getName());
    exercise.setDate(exRequest.getDate());
    exercise.setType(typeExercise);

    return exercise;
  }

  public static ExResponse toResponse(Exercise exercise) {
    ExResponse exResponse = new ExResponse();
    exResponse.setId(exercise.getId());
    exResponse.setName(exercise.getName());
    exResponse.setDate(exercise.getDate());
    exResponse.setType(TypeExConvert.toResponse(exercise.getType()));

    return exResponse;
  }

  public static List<ExResponse> toResponseList(List<Exercise> exercises) {
    List<ExResponse> exResponses = new ArrayList<>();
    for (Exercise exercise : exercises) {
      exResponses.add(toResponse(exercise));
    }

    return exResponses;
  }

}
