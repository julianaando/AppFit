package com.ada.meuPrimeiroProjeto.interfaces;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;

import java.util.List;

public interface ITypeExerciseService {

  List<TypeExerciseResponse> getAllTypeExercises();

  TypeExerciseResponse saveTypeExercise(TypeExerciseRequest typeExerciseRequest);

  TypeExerciseResponse updateTypeExercise(Integer id, TypeExerciseRequest typeExerciseRequest);

  void deleteTypeExercise(Integer id);

}
