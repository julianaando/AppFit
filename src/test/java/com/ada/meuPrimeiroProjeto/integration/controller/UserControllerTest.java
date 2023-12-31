package com.ada.meuPrimeiroProjeto.integration.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.meuPrimeiroProjeto.service.UserService;
import com.ada.meuPrimeiroProjeto.utils.UserConvert;
import java.util.List;
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
public class UserControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_register_user() throws Exception {
    UserResponse userResponse = new UserResponse();
    userResponse.setId(10);
    userResponse.setName("Maria");
    userResponse.setEmail("maria@email.com");

    Mockito.when(userService.saveUser(Mockito.any())).thenReturn(userResponse);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/user")
          .content("""
                      {
                          "name": "Maria",
                          "email": "maria@email.com",
                          "password": "12345@Maria"
                      }
                  """)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()
      );
  }

  @Test
  public void should_not_be_possible_to_register_user_without_name() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "email": "test@email.com",
            "password": "123456@Teste",
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andDo(MockMvcResultHandlers.print()
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void should_not_be_possible_to_register_user_without_email() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "name": ["Maria"],
            "password": ["123456@Maria"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andDo(MockMvcResultHandlers.print()
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void should_not_be_possible_to_register_user_without_password() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "name": ["Maria"],
            "email": ["maria@test.com"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andDo(MockMvcResultHandlers.print()
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }


  @Test
  public void should_not_be_possible_to_register_user_with_invalid_password() throws Exception {
    Mockito.when(userService.saveUser(Mockito.any())).thenThrow(PasswordValidationError.class);

    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "name": ["Maria"],
            "email": ["maria@teste.com"],
            "password": ["123"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andDo(MockMvcResultHandlers.print()
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

 @Test
  public void should_list_all_users() throws Exception {
    UserResponse user = new UserResponse();
    user.setId(10);
    user.setName("Maria");
    user.setEmail("maria@email.com");
    Mockito.when(userService.getUsers()).thenReturn(List.of(user));

    mockMvc.perform(
      MockMvcRequestBuilders.get("/user")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isOk()
    ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Maria"));
  }

  @Test
  public void should_get_user_by_id() throws Exception {
    UserResponse user = new UserResponse();
    user.setId(10);
    user.setName("Maria");
    user.setEmail("maria@test.com");

    Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(user);

    mockMvc.perform(
      MockMvcRequestBuilders.get("/user/10")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isOk()
    ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Maria"));
  }
}
