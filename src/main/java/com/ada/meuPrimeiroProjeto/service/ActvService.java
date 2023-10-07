package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.ActvRepository;
import com.ada.meuPrimeiroProjeto.repository.ExRepository;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;
import com.ada.meuPrimeiroProjeto.utils.ActvConvert;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActvService {
  @Autowired
  ActvRepository actvRepository;

  @Autowired
  ExRepository exRepository;

  @Autowired
  UserRepository userRepository;


  public ActvResponse saveActv(ActvRequest actvRequest) {
    User user = userRepository.findById(actvRequest.getUserId()).get();

    List<Exercise> exercises = new ArrayList<>();
    List<Integer> exercisesId = actvRequest.getExercisesIds();

    for (Integer id : exercisesId) {
      Exercise exercise = exRepository.findById(id).get();
      exercises.add(exercise);
    }

    Activities actv = ActvConvert.toEntity(user, exercises);

    return ActvConvert.toResponse(actvRepository.save(actv));
  }

  public List<ActvResponse> getAllActivities(Integer userId, Integer exerciseId) {
    if (userId != null) {
      return getAllByUser(userId);
    } else if (exerciseId != null) {
      return getAllByExercise(exerciseId);
    } else {
      return ActvConvert.toResponseList(actvRepository.findAll());
    }
  }

  public List<ActvResponse> getAllByUser(Integer userId) {
    return ActvConvert.toResponseList(actvRepository.findAllByUser(userId));
  }

  public List<ActvResponse> getAllByExercise(Integer exerciseId) {
    return ActvConvert.toResponseList(actvRepository.findAllByExercise(exerciseId));
  }

}
