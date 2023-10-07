package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.TypeExerciseRepository;
import com.ada.meuPrimeiroProjeto.utils.TypeExerciseConvert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeExerciseService {

  @Autowired
  TypeExerciseRepository typeExerciseRepository;

  public List<TypeExerciseResponse> getAllTypeExercises(){
    return TypeExerciseConvert.toResponseList(typeExerciseRepository.findAll());
  }

  public TypeExerciseResponse saveTypeExercise(TypeExerciseRequest typeExerciseRequest){
    TypeExercise typeExercise = typeExerciseRepository.save(
      TypeExerciseConvert.toEntity(typeExerciseRequest)
    );
    return TypeExerciseConvert.toResponse(typeExercise);
  }

  public TypeExerciseResponse updateTypeExercise(Integer id, TypeExerciseRequest typeExerciseRequest) {
    TypeExercise type = TypeExerciseConvert.toEntity(typeExerciseRequest);
    type.setId(id);
    TypeExercise typeEntity = typeExerciseRepository.save(type);
    return TypeExerciseConvert.toResponse(typeEntity);
  }

  public void deleteTypeExercise(Integer id){
    typeExerciseRepository.deleteById(id);
  }

}
