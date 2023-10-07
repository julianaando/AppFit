package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesResponse;
import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.ActivitiesRepository;
import com.ada.meuPrimeiroProjeto.repository.ExerciseRepository;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;
import com.ada.meuPrimeiroProjeto.utils.ActivitiesConvert;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesService {
  @Autowired
  ActivitiesRepository activitiesRepository;

  @Autowired
  ExerciseRepository exerciseRepository;

  @Autowired
  UserRepository userRepository;


  public ActivitiesResponse saveActivities(ActivitiesRequest activitiesRequest) {
    User user = userRepository.findById(activitiesRequest.getUserId()).get();

    List<Exercise> exercises = new ArrayList<>();
    List<Integer> exercisesId = activitiesRequest.getExercisesIds();

    for (Integer id : exercisesId) {
      Exercise exercise = exerciseRepository.findById(id).get();
      exercises.add(exercise);
    }

    Activities actv = ActivitiesConvert.toEntity(user, exercises);

    return ActivitiesConvert.toResponse(activitiesRepository.save(actv));
  }

  public List<ActivitiesResponse> getAllActivities(Integer userId, Integer exerciseId) {
    if (userId != null) {
      return getAllByUser(userId);
    } else if (exerciseId != null) {
      return getAllByExercise(exerciseId);
    } else {
      return ActivitiesConvert.toResponseList(activitiesRepository.findAll());
    }
  }

  public List<ActivitiesResponse> getAllByUser(Integer userId) {
    if (userId == null) {
      throw new RuntimeException("Usuário não encontrado!");
    }
    return ActivitiesConvert.toResponseList(activitiesRepository.findAllByUser(userId));
  }

  public List<ActivitiesResponse> getAllByExercise(Integer exerciseId) {
    if (exerciseId == null) {
      throw new RuntimeException("Exercício não encontrado!");
    }
    return ActivitiesConvert.toResponseList(activitiesRepository.findAllByExercise(exerciseId));
  }

}
