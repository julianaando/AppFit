package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.TypeExerciseRepository;
import com.ada.meuPrimeiroProjeto.utils.TypeExerciseConvert;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeExerciseService {

  private final TypeExerciseRepository typeExerciseRepository;

  @Autowired
  public TypeExerciseService(TypeExerciseRepository typeExerciseRepository) {
    this.typeExerciseRepository = typeExerciseRepository;
  }

  public List<TypeExerciseResponse> getAllTypeExercises(){
    return TypeExerciseConvert.toResponseList(typeExerciseRepository.findAll());
  }

  public TypeExerciseResponse saveTypeExercise(TypeExerciseRequest typeExerciseRequest){
    TypeExercise typeExercise = TypeExerciseConvert.toEntity(typeExerciseRequest);
    if (typeExercise.getName() == null) {
      throw new IllegalArgumentException("Nome do tipo de exercício não pode ser vazio!");
    }
    return TypeExerciseConvert.toResponse(typeExerciseRepository.save(typeExercise));
  }

  public TypeExerciseResponse updateTypeExercise(Integer id, TypeExerciseRequest typeExerciseRequest) {
    TypeExercise type = TypeExerciseConvert.toEntity(typeExerciseRequest);
    type.setId(id);
    TypeExercise typeEntity = typeExerciseRepository.save(type);
    return TypeExerciseConvert.toResponse(typeEntity);
  }

  public void deleteTypeExercise(Integer id){
    Optional<TypeExercise> typeExerciseResponse = typeExerciseRepository.findById(id);
    if (typeExerciseResponse.isPresent()) {
      typeExerciseRepository.delete(typeExerciseResponse.get());
    } else {
      throw new RuntimeException("Tipo de exercício não encontrado!");
    }
  }

}
