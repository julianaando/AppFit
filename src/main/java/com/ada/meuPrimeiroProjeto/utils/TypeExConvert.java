package com.ada.meuPrimeiroProjeto.utils;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import java.util.ArrayList;
import java.util.List;

public class TypeExConvert {

  public static TypeExercise toEntity(TypeExRequest typeExRequest) {
    TypeExercise typeExercise = new TypeExercise();
    typeExercise.setName(typeExRequest.getName());
    return typeExercise;
  }

  public static TypeExResponse toResponse(TypeExercise typeExercise) {
    TypeExResponse typeExResponse = new TypeExResponse();
    typeExResponse.setId(typeExercise.getId());
    typeExResponse.setName(typeExercise.getName());
    return typeExResponse;
  }

  public static List<TypeExResponse> toResponseList(List<TypeExercise> typesExercises) {
    List<TypeExResponse> typesExercisesResponses = new ArrayList<>();
    for (TypeExercise typeExercise : typesExercises) {
      typesExercisesResponses.add(toResponse(typeExercise));
    }

    return typesExercisesResponses;
  }

}
