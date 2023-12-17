package com.ada.meuPrimeiroProjeto.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.repository.TypeExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TypeExerciseServiceTest {

  @Mock
  private TypeExerciseRepository typeExerciseRepository;

  @InjectMocks
  private TypeExerciseService typeExerciseService;

  @Test
  public void test_get_all_type_exercises_success() {
    List<TypeExercise> typeExerciseList = List.of(new TypeExercise(), new TypeExercise());
    Mockito.when(typeExerciseRepository.findAll()).thenReturn(typeExerciseList);

    List<TypeExerciseResponse> result = typeExerciseService.getAllTypeExercises();

    assertNotNull(result);
    assertEquals(typeExerciseList.size(), result.size());
  }

  @Test
  public void test_save_type_exercise_success() {
    TypeExerciseRequest typeExerciseRequest = new TypeExerciseRequest("TestType");
    TypeExercise savedTypeExercise = new TypeExercise();
    savedTypeExercise.setId(1);
    savedTypeExercise.setName("TestType");
    Mockito.when(typeExerciseRepository.save(Mockito.any(TypeExercise.class))).thenReturn(savedTypeExercise);

    TypeExerciseResponse result = typeExerciseService.saveTypeExercise(typeExerciseRequest);

    assertNotNull(result);
    assertEquals(savedTypeExercise.getId(), result.getId());
    assertEquals(savedTypeExercise.getName(), result.getName());
  }

  @Test
  public void test_update_type_exercise_success() {
    int typeId = 1;
    TypeExerciseRequest typeExerciseRequest = new TypeExerciseRequest("UpdatedType");
    TypeExercise existingTypeExercise = new TypeExercise();
    existingTypeExercise.setId(typeId);
    existingTypeExercise.setName("OriginalType");

    Mockito.when(typeExerciseRepository.findById(typeId)).thenReturn(Optional.of(existingTypeExercise));
    Mockito.when(typeExerciseRepository.save(Mockito.any(TypeExercise.class))).thenAnswer(invocation -> invocation.getArgument(0));

    TypeExerciseResponse result = typeExerciseService.updateTypeExercise(typeId, typeExerciseRequest);

    assertNotNull(result);
    assertEquals(existingTypeExercise.getId(), result.getId());
    assertEquals(typeExerciseRequest.getName(), result.getName());
  }

  @Test
  public void test_delete_type_exercise_success() {
    int typeId = 1;
    TypeExercise existingTypeExercise = new TypeExercise();
    existingTypeExercise.setId(typeId);
    existingTypeExercise.setName("OriginalType");

    Mockito.when(typeExerciseRepository.findById(typeId)).thenReturn(Optional.of(existingTypeExercise));

    typeExerciseService.deleteTypeExercise(typeId);

    Mockito.verify(typeExerciseRepository, Mockito.times(1)).delete(existingTypeExercise);
  }

}
