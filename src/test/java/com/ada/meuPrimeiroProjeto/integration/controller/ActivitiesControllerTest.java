package com.ada.meuPrimeiroProjeto.integration.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesResponse;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.service.ActivitiesService;
import com.ada.meuPrimeiroProjeto.service.ExerciseService;
import com.ada.meuPrimeiroProjeto.service.UserService;
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
public class ActivitiesControllerTest {

  ActivitiesResponse activitiesResponse;

  Exercise exercise;

  User user;

  @MockBean
  private ActivitiesService activitiesService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    activitiesResponse = new ActivitiesResponse();
    activitiesResponse.setId(10);
    activitiesResponse.setExercises(List.of(new Exercise()));
    activitiesResponse.setUser(new User());

  }

  @Test
  public void should_register_activities() throws Exception {
    Mockito.when(activitiesService.saveActivities(Mockito.any())).thenReturn(activitiesResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.post("/activities")
        .content("""
          {
            "userId": 150,
            "exercisesIds": [12],
            "date": "2021-10-10"
            }
          }
        """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()
    );
  }

  @Test
  public void should_be_possible_get_activities() throws Exception {
    Mockito.when(activitiesService.getAllActivities(Mockito.anyInt())).thenReturn(List.of(activitiesResponse));

    mockMvc.perform(
      MockMvcRequestBuilders.get("/activities")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void should_get_activities_by_id() throws Exception {
    Mockito.when(activitiesService.getActivitiesById(Mockito.anyInt())).thenReturn(activitiesResponse);

    mockMvc.perform(
      MockMvcRequestBuilders.get("/activities/10")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10));
  }
}
