package com.ada.meuPrimeiroProjeto.integration.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.service.TypeExerciseService;
import jakarta.persistence.Table;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TypeExerciseControllerTest {

  TypeExerciseResponse typeExerciseResponse;

  @MockBean
  private TypeExerciseService typeExerciseService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    typeExerciseResponse = new TypeExerciseResponse();
    typeExerciseResponse.setId(10);
  }

  @Test
  public void should_register_type_exercise() throws Exception {
    Mockito.when(typeExerciseService.saveTypeExercise(Mockito.any())).thenReturn(typeExerciseResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.post("/type-exercise")
        .content("""
          {
            "name": "cardio"
          }
        """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()
      );
  }

  @Test
  public void should_update_type_exercise() throws Exception {
    Mockito.when(typeExerciseService.updateTypeExercise(Mockito.anyInt(), Mockito.any())).thenReturn(typeExerciseResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.put("/type-exercise/10")
        .content("""
          {
            "name": "cardio"
          }
        """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()
      );
  }

  @Test
  public void should_get_all_type_exercises() throws Exception {
    Mockito.when(typeExerciseService.getAllTypeExercises()).thenReturn(List.of(typeExerciseResponse));

    mockMvc.perform(
      MockMvcRequestBuilders.get("/type-exercise")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void should_delete_type_exercise_by_id() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.delete("/type-exercise/10")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
