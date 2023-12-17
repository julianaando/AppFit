package com.ada.meuPrimeiroProjeto.service;


import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.ExerciseRepository;
import com.ada.meuPrimeiroProjeto.repository.TypeExerciseRepository;
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
class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @Mock
  private TypeExerciseRepository typeExerciseRepository;

  @InjectMocks
  private ExerciseService exerciseService;

  private Exercise exercise;

  private TypeExercise typeExercise;

  @BeforeEach
  void setUp() {
    exercise = new Exercise();

    typeExercise = new TypeExercise();
    typeExercise.setId(1);
    typeExercise.setName("aerobic");
    exercise.setType(typeExercise);

    Mockito.when(typeExerciseRepository.save(Mockito.any())).thenReturn(typeExercise);

    Mockito.when(typeExerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(typeExercise));
  }

  @Test
  public void test_save_exercise_success() {
    Mockito.when(exerciseRepository.save(Mockito.any())).thenReturn(exercise);

    ExerciseResponse exerciseResponse = exerciseService.saveExercise(new ExerciseRequest(
      "jogging",
      1
    ));

    Assertions.assertNotNull(exerciseResponse);
  }

  @Test
  public void test_save_exercise_fail() {
    Mockito.when(exerciseRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, () -> exerciseService.saveExercise(new ExerciseRequest(
      "jogging",
      2
    )));
  }

  @Test
  public void test_get_all_exercises_success() {
    Mockito.when(exerciseRepository.findAll()).thenReturn(List.of(exercise));

    List<ExerciseResponse> exerciseResponseList = exerciseService.getAllExercises();

    Assertions.assertNotNull(exerciseResponseList);
    Assertions.assertFalse(exerciseResponseList.isEmpty());
  }

  @Test
  public void test_get_all_exercises_fail() {
    Mockito.when(exerciseRepository.findAll()).thenReturn(List.of());

    Assertions.assertThrows(RuntimeException.class, () -> exerciseService.getAllExercises());
  }

  @Test
  public void test_delete_exercise_success() {
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(exercise));

    exerciseService.deleteExercise(1);

    Assertions.assertFalse(exercise.getActive());
  }

  @Test
  public void test_delete_exercise_fail() {
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    Assertions.assertThrows(RuntimeException.class, () -> exerciseService.deleteExercise(1));
  }

  @Test
  public void test_get_exercise_by_id_success() {
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(exercise));

    ExerciseResponse exerciseResponse = exerciseService.getExerciseById(1);

    Assertions.assertNotNull(exerciseResponse);
  }

  @Test
  public void test_get_exercise_by_id_fail() {
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    Assertions.assertThrows(RuntimeException.class, () -> exerciseService.getExerciseById(1));
  }

  @Test
  public void test_update_exercise_success() {
    exercise.setId(1);
    exercise.setName("push-up");
    exercise.setActive(true);
    exercise.setType(typeExercise);

    Mockito.when(exerciseRepository.save(Mockito.any())).thenReturn(exercise);
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(exercise));

    ExerciseResponse exerciseResponse = exerciseService.updateExercise(1, new ExerciseRequest(
      "jogging",
      1
    ));

    Assertions.assertNotNull(exerciseResponse);
  }

  @Test
  public void test_update_exercise_fail() {
    Mockito.when(exerciseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    Assertions.assertThrows(RuntimeException.class, () -> exerciseService.updateExercise(1, new ExerciseRequest(
      "jogging",
      1
    )));
  }
}