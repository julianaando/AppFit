package com.ada.MeuPrimeiroProjeto.utils;

import com.ada.MeuPrimeiroProjeto.controller.dto.ExRequest;
import com.ada.MeuPrimeiroProjeto.model.Exercise;

public class ExConvert {

  public static Exercise toEntity(ExRequest exRequest) {
    Exercise exercise = new Exercise();
    exercise.setName(exRequest.getName());
    exercise.setReps(exRequest.getReps());
    exercise.setWeight(exRequest.getWeight());
    exercise.setSets(exRequest.getSets());
    exercise.setRest(exRequest.getRest());

    return exercise;
  }

}
