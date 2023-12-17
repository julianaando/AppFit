package com.ada.meuPrimeiroProjeto.integration.controller.user;

import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.interfaces.IUserService;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.service.UserService;
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
public class UserControllerIntegrationTest {

  @MockBean
  private IUserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_not_be_possible_to_register_user_without_name() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "email": ["test@email.com"],
            "password": ["123456Teste"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void should_not_be_possible_to_register_user_without_email() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "name": ["Teste"],
            "password": ["123456Teste"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void should_not_be_possible_to_register_user_without_password() throws Exception {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
        .content("""
          {
            "name": ["Teste"],
            "email": ["teste@email.com"],
          }
          """)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

/*  @Test
  public void should_list_all_users() throws Exception {
    User user = new User();
    user.setId(10);
    user.setName("Maria");
    user.setEmail("maria@email.com");
    user.setPassword("123456");
    Mockito.when(userService.getUsers()).thenReturn(List.of(user));

    // O teste garante que ao receber um cliente sem a informação de nome a
    // aplicação irá retornar o status code 400
    mockMvc.perform(
      MockMvcRequestBuilders.get("/customers")
        .accept(MediaType.APPLICATION_JSON)
    ).andDo(
      MockMvcResultHandlers.print()
    ).andExpect(//andExpect é um assert dessa forma de teste
      MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
      MockMvcResultMatchers.jsonPath("$[0].name")
        .value("Will")
    );
  }*/
}
