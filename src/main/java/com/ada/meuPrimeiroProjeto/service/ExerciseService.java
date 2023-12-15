package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.interfaces.IExerciseService;
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
public class ExerciseService implements IExerciseService {

  private final ExerciseRepository exerciseRepository;

  private final TypeExerciseRepository typeExerciseRepository;

  @Autowired
  public ExerciseService(
    ExerciseRepository exerciseRepository,
    TypeExerciseRepository typeExerciseRepository
  ) {
    this.exerciseRepository = exerciseRepository;
    this.typeExerciseRepository = typeExerciseRepository;
  }

  @Override
  public ExerciseResponse saveExercise(ExerciseRequest exerciseRequest) {
    TypeExercise typeExercise = typeExerciseRepository
      .findById(exerciseRequest.getTypeId())
      .orElse(null);
    Exercise exercise = ExerciseConvert.toEntity(exerciseRequest, typeExercise);
    exercise.setActive(true);
    return ExerciseConvert.toResponse(exerciseRepository.save(exercise));
  }

  @Override
  public List<ExerciseResponse> getAllExercises() {
    List<Exercise> exerciseResponse = exerciseRepository.findAll();
    if (exerciseResponse.isEmpty()) {
      throw new RuntimeException("Não há exercícios cadastrados!");
    }
    return ExerciseConvert.toResponseList(exerciseRepository.findAll());
  }

  @Override
  public void deleteExercise(Integer id) {
    Optional<Exercise> exerciseResponse = exerciseRepository.findById(id);
    if (exerciseResponse.isPresent()) {
      exerciseResponse.get().setActive(false);
      exerciseRepository.delete(exerciseResponse.get());
    } else {
      throw new IllegalArgumentException("Exercício não encontrado!");
    }
  }

  @Override
  public ExerciseResponse getExerciseById(Integer id) {
    Optional<Exercise> exerciseResponse = exerciseRepository.findById(id);
    if (exerciseResponse.isPresent()) {
      return ExerciseConvert.toResponse(exerciseResponse.get());
    } else {
      throw new IllegalArgumentException("Exercício não encontrado!");
    }
  }

  @Override
  public ExerciseResponse updateExercise(
    Integer id,
    ExerciseRequest exerciseRequest
  ) {
    Exercise exercise = exerciseRepository.findById(id).orElse(null);
    TypeExercise typeExercise = typeExerciseRepository.findById(
      exerciseRequest.getTypeId()
    ).orElse(null);
    if (exercise != null && typeExercise != null) {
      Exercise exerciseEntity = ExerciseConvert.toEntity(exerciseRequest, typeExercise);
      exerciseEntity.setId(id);
      return ExerciseConvert.toResponse(exerciseRepository.save(exerciseEntity));
    } else {
      throw new IllegalArgumentException("Exercício não encontrado!");
    }
  }
}
