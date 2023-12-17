package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.controller.exception.EmailNotFoundException;
import com.ada.meuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.meuPrimeiroProjeto.controller.exception.UserNotFoundException;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserService userService;

  @Test
  public void test_get_users_success() {
    User user = new User();
    user.setId(1);
    user.setName("Nome");
    user.setEmail("email@email.com");
    user.setPassword("Senha@123");

    Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

    List<UserResponse> userResponse = userService.getUsers();
    Assertions.assertNotNull(userResponse);
  }

  @Test
  public void test_get_users_fail() {
    Assertions.assertThrows(UserNotFoundException.class,
      () -> userService.getUsers()
    );
  }

  @Test
  public void test_get_user_by_email_fail() {
    Assertions.assertThrows(EmailNotFoundException.class,
      () -> userService.getUserByEmail("invalid")
    );
  }

  @Test
  public void test_get_user_by_email_success() {
    User user = new User();
    user.setId(1);
    user.setName("Nome");
    user.setEmail("email");
    user.setPassword("Senha@123");

    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

    UserResponse response = userService.getUserByEmail("email");
    Assertions.assertNotNull(response);
  }

  @Test
  public void test_get_all_by_name_success() {
    User user = new User();
    user.setId(1);
    user.setName("Nome");
    user.setEmail("email");
    user.setPassword("Senha@123");

    Mockito.when(userRepository.findAllByName(Mockito.anyString())).thenReturn(List.of(user));

    List<UserResponse> response = userService.getAllByName("Nome");
    Assertions.assertNotNull(response);
  }

  @Test
  public void test_get_all_by_name_fail() {
    Assertions.assertThrows(RuntimeException.class,
      () -> userService.getAllByName("invalid")
    );
  }

  @Test
  public void test_get_user_by_id_fail() {
    Assertions.assertThrows(UserNotFoundException.class,
      () -> userService.getUserById(0)
    );
  }

  @Test
  public void test_delete_user_fail() {
    Assertions.assertThrows(UserNotFoundException.class,
      () -> userService.deleteUser(0)
    );
  }

  @Test
  public void test_update_user_fail() {
    Assertions.assertThrows(NullPointerException.class,
      () -> userService.updateUser(1, new UserRequest(
        "Nome",
        "email",
        "Senha@123"
      ))
    );
  }

  @Test
  public void test_save_user_fail() {
    Assertions.assertThrows(PasswordValidationError.class,
      () -> userService.saveUser(new UserRequest(
        "Teste",
        "email@teste.com",
        "invalid"
      ))
    );
  }

  @Test
  public void test_save_user_success() throws PasswordValidationError {
    User user = new User();
    user.setId(1);
    user.setName("Teste");
    user.setEmail("email@teste.com");
    user.setPassword("123@Teste");

    UserRequest userRequest = new UserRequest(
      user.getName(),
      user.getEmail(),
      user.getPassword()
    );

    Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

    userService.saveUser(userRequest);
  }

}