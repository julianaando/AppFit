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
import java.util.Optional;
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
    Optional<User> user = userRepository.findById(activitiesRequest.getUserId());

    if (user.isEmpty()) {
      throw new IllegalArgumentException("Usuário não encontrado!");
    }

    List<Exercise> exercises = new ArrayList<>();
    List<Integer> exercisesId = activitiesRequest.getExercisesIds();

    for (Integer id : exercisesId) {
      Optional<Exercise> exercise = exerciseRepository.findById(id);
      if (exercise.isEmpty()) {
        throw new IllegalArgumentException("Exercício não encontrado!");
      }
      exercises.add(exercise.get());
    }

    Activities activity = ActivitiesConvert.toEntity(user.get(), exercises, activitiesRequest);

    return ActivitiesConvert.toResponse(activitiesRepository.save(activity));
  }

  public List<ActivitiesResponse> getAllActivities(Integer userId) {
    if (userId != null) {
      return getAllByUser(userId);
    } else {
      return ActivitiesConvert.toResponseList(activitiesRepository.findAll());
    }
  }

  private List<ActivitiesResponse> getAllByUser(Integer userId) {
    return ActivitiesConvert.toResponseList(activitiesRepository.findAllByUser(userId));
  }

  public ActivitiesResponse getActivitiesById(Integer id) {
    Optional<Activities> activitiesResponse = activitiesRepository.findById(id);
    if (activitiesResponse.isPresent()) {
      return ActivitiesConvert.toResponse(activitiesResponse.get());
    } else {
      throw new IllegalArgumentException("Atividade não encontrada!");
    }
  }
}
