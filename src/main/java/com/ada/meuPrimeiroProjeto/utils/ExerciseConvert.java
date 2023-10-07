package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import java.util.ArrayList;
import java.util.List;

public class ExerciseConvert {

  public static Exercise toEntity(ExerciseRequest exerciseRequest, TypeExercise typeExercise) {
    Exercise exercise = new Exercise();
    exercise.setName(exerciseRequest.getName());
    exercise.setDate(exerciseRequest.getDate());
    exercise.setType(typeExercise);

    return exercise;
  }

  public static ExerciseResponse toResponse(Exercise exercise) {
    ExerciseResponse exerciseResponse = new ExerciseResponse();
    exerciseResponse.setId(exercise.getId());
    exerciseResponse.setName(exercise.getName());
    exerciseResponse.setDate(exercise.getDate());
    exerciseResponse.setType(TypeExerciseConvert.toResponse(exercise.getType()));

    return exerciseResponse;
  }

  public static List<ExerciseResponse> toResponseList(List<Exercise> exercises) {
    List<ExerciseResponse> exerciseResponse = new ArrayList<>();
    for (Exercise exercise : exercises) {
      exerciseResponse.add(toResponse(exercise));
    }

    return exerciseResponse;
  }

}
