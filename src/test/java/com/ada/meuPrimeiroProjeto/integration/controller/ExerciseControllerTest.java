package com.ada.meuPrimeiroProjeto.integration.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.service.ExerciseService;
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
public class ExerciseControllerTest {

  ExerciseResponse exerciseResponse;

  @MockBean
  private ExerciseService exerciseService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    exerciseResponse = new ExerciseResponse();
    exerciseResponse.setId(10);
    exerciseResponse.setName("spinning");
    exerciseResponse.setType(new TypeExerciseResponse());
  }

  @Test
  public void should_register_exercise() throws Exception {
    Mockito.when(exerciseService.saveExercise(Mockito.any())).thenReturn(exerciseResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.post("/exercise")
        .content("""
          {
            "id": 10,
            "name": "spinning",
            "type": {
              "id": 1
            }
          }
        """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()
      );
  }

  @Test
  public void should_be_possible_to_get_exercises() throws Exception {
    Mockito.when(exerciseService.getAllExercises()).thenReturn(List.of(exerciseResponse));

    mockMvc.perform(
      MockMvcRequestBuilders.get("/exercise")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void should_be_possible_to_get_exercise_by_id() throws Exception {
    Mockito.when(exerciseService.getExerciseById(Mockito.anyInt())).thenReturn(exerciseResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.get("/exercise/10")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void should_be_possible_to_delete_exercise_by_id() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.delete("/exercise/10")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
