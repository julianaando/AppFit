package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.TypeExRepository;
import com.ada.meuPrimeiroProjeto.utils.TypeExConvert;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeExService {

  @Autowired
  TypeExRepository typeExRepository;

  public List<TypeExResponse> getAllTypeExercises(){
    return TypeExConvert.toResponseList(typeExRepository.findAll());
  }

  public TypeExResponse saveTypeExercise(TypeExRequest typeExRequest){
    TypeExercise typeExercise = typeExRepository.save(
      TypeExConvert.toEntity(typeExRequest)
    );
    return TypeExConvert.toResponse(typeExercise);
  }

  public TypeExResponse updateTypeExercise(Integer id, TypeExRequest typeExRequest) {
    TypeExercise type = TypeExConvert.toEntity(typeExRequest);
    type.setId(id);
    TypeExercise typeEntity = typeExRepository.save(type);
    return TypeExConvert.toResponse(typeEntity);
  }

  public void deleteTypeExercise(Integer id){
    typeExRepository.deleteById(id);
  }

}
