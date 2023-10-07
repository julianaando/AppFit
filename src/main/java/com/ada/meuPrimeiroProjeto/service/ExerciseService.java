package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.ExerciseRepository;
import com.ada.meuPrimeiroProjeto.repository.TypeExerciseRepository;
import com.ada.meuPrimeiroProjeto.utils.ExerciseConvert;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

  @Autowired
  ExerciseRepository exerciseRepository;

  @Autowired
  TypeExerciseRepository typeExerciseRepository;

  public ExerciseResponse saveExercise(ExerciseRequest exerciseRequest){
    TypeExercise typeExercise = typeExerciseRepository.findById(exerciseRequest.getTypeId()).get();
    Exercise exercise = ExerciseConvert.toEntity(exerciseRequest, typeExercise);
    return ExerciseConvert.toResponse(exerciseRepository.save(exercise));
  }

  public List<ExerciseResponse> getAllExercise(Integer typeExercise){
    if(typeExercise != null){
      return getAllByTypeExercise(typeExercise);
    }
    return ExerciseConvert.toResponseList(exerciseRepository.findAll());
  }

  private List<ExerciseResponse> getAllByTypeExercise(Integer typeExercise) {
    return ExerciseConvert.toResponseList(exerciseRepository.findExerciseByType(typeExercise));
  }

  public List<ExerciseResponse> getAllByDate(LocalDate date) {
    List<Exercise> exercises = exerciseRepository.findExerciseByDate(date);
    if (exercises.isEmpty()) {
      throw new RuntimeException("Não há exercício registrado nesta data!");
    }
    return ExerciseConvert.toResponseList(exerciseRepository.findExerciseByDate(date));
  }

  public void deleteExercise(Integer id) {
    Exercise exercise = exerciseRepository.findById(id).orElseThrow();
    exerciseRepository.save(exercise);
  }

  public ExerciseResponse getExerciseById(Integer id){
    Optional<Exercise> exerciseResponse =  exerciseRepository.findById(id);
    if(exerciseResponse.isPresent()){
      return ExerciseConvert.toResponse(exerciseResponse.get());
    } else {
      throw new RuntimeException("Exercício não encontrado!");
    }
  }

  public ExerciseResponse updateExercise(Integer id, ExerciseRequest exerciseRequest, TypeExercise typeExercise){
    Exercise exercise = ExerciseConvert.toEntity(exerciseRequest, typeExercise);
    exercise.setId(id);
    Exercise exerciseEntity = exerciseRepository.save(exercise);

    return ExerciseConvert.toResponse(exerciseEntity);
  }
}
