package com.ada.meuPrimeiroProjeto.interfaces;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import java.util.List;

public interface IExerciseService {

  ExerciseResponse saveExercise(ExerciseRequest exerciseRequest);

  List<ExerciseResponse> getAllExercises();

  void deleteExercise(Integer id);

  ExerciseResponse getExerciseById(Integer id);

  ExerciseResponse updateExercise(Integer id, ExerciseRequest exerciseRequest);

}
