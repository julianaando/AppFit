package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import java.util.ArrayList;
import java.util.List;

public class TypeExerciseConvert {

  public static TypeExercise toEntity(TypeExerciseRequest typeExerciseRequest) {
    TypeExercise typeExercise = new TypeExercise();
    typeExercise.setName(typeExerciseRequest.getName());
    return typeExercise;
  }

  public static TypeExerciseResponse toResponse(TypeExercise typeExercise) {
    TypeExerciseResponse typeExerciseResponse = new TypeExerciseResponse();
    typeExerciseResponse.setId(typeExercise.getId());
    typeExerciseResponse.setName(typeExercise.getName());
    return typeExerciseResponse;
  }

  public static List<TypeExerciseResponse> toResponseList(List<TypeExercise> typesExercises) {
    List<TypeExerciseResponse> typesExercisesResponses = new ArrayList<>();
    for (TypeExercise typeExercise : typesExercises) {
      typesExercisesResponses.add(toResponse(typeExercise));
    }

    return typesExercisesResponses;
  }

}
