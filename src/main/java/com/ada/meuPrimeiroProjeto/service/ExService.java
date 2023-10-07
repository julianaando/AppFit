package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.ExRepository;
import com.ada.meuPrimeiroProjeto.repository.TypeExRepository;
import com.ada.meuPrimeiroProjeto.utils.ExConvert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExService {

  @Autowired
  ExRepository exRepository;

  @Autowired
  TypeExRepository typeExRepository;

  public ExResponse saveExercise(ExRequest exRequest){
    TypeExercise typeExercise = typeExRepository.findById(exRequest.getTypeId()).get();
    Exercise exercise = ExConvert.toEntity(exRequest, typeExercise);
    return ExConvert.toResponse(exRepository.save(exercise));
  }

  public List<ExResponse> getAllExercise(Integer typeExercise){
    if(typeExercise != null){
      return getAllByTypeExercise(typeExercise);
    }
    return ExConvert.toResponseList(exRepository.findAll());
  }

  private List<ExResponse> getAllByTypeExercise(Integer typeExercise) {
    return ExConvert.toResponseList(exRepository.findExerciseByType(typeExercise));
  }

  public List<ExResponse> getAllByDate(String date) {
    return ExConvert.toResponseList(exRepository.findExerciseByDate(date));
  }
}
