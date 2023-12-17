package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesResponse;
import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.ActivitiesRepository;
import com.ada.meuPrimeiroProjeto.repository.ExerciseRepository;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ActivitiesServiceTest {
  private Exercise exercise;

  private User user;
  private Activities activities;

  @Mock
  private ActivitiesRepository activitiesRepository;

  @Mock
  private ExerciseRepository exerciseRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private ActivitiesService activitiesService;

  @BeforeEach
  void setUp() {
    activities = new Activities();

    exercise = new Exercise();
    exercise.setId(5);

    user = new User();
    user.setId(1);

    Mockito.when(exerciseRepository.save(Mockito.any())).thenReturn(exercise);
    Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(exercise));
    Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
  }

  @Test
  public void test_save_activities_success() {
    Mockito.when(activitiesRepository.save(Mockito.any())).thenReturn(activities);

    ActivitiesResponse activitiesResponse = activitiesService.saveActivities(new ActivitiesRequest(
      user.getId(),
      List.of(exercise.getId()),
      LocalDate.of(2021, 10, 10)
    ));

    Assertions.assertNotNull(activitiesResponse);
  }

  @Test
  public void test_get_activities_by_id_success() {
    activities.setId(1);
    Mockito.when(activitiesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(activities));

    ActivitiesResponse activitiesResponse = activitiesService.getActivitiesById(activities.getId());

    Assertions.assertNotNull(activitiesResponse);
  }

  @Test
  public void test_get_all_activities_success() {
    Mockito.when(activitiesRepository.findAll()).thenReturn(List.of(activities));

    List<ActivitiesResponse> activitiesResponseList = activitiesService.getAllActivities(null);

    Assertions.assertNotNull(activitiesResponseList);
    Assertions.assertFalse(activitiesResponseList.isEmpty());
  }
}
