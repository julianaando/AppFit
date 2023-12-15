package com.ada.meuPrimeiroProjeto.service;

import com.ada.meuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.meuPrimeiroProjeto.model.User;
import com.ada.meuPrimeiroProjeto.repository.UserRepository;

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
public class UserServiceTest {
  private User user;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;


  @BeforeEach
  public void setUp() {
    user = new User();

  }

  @Test
  public void test_get_users_success() {
    Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

    List<UserResponse> userResponseList = userService.getUsers();

    Assertions.assertNotNull(userResponseList);
    Assertions.assertFalse(userResponseList.isEmpty());
  }

  @Test
  public void test_get_users_fail() {
    Mockito.when(userRepository.findAll()).thenReturn(List.of());

    Assertions.assertThrows(RuntimeException.class, () -> userService.getUsers());
  }

  @Test
  public void test_save_user_success() {
    Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

    UserResponse userResponse = userService.saveUser(new UserRequest(
      "Ana",
      "ana@email.com",
      "12345@Ana"
    ));

    Assertions.assertNotNull(userResponse);
  }

  @Test
  public void test_save_user_fail() {
    Mockito.when(userRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, () -> userService.saveUser(new UserRequest(
      "",
      "ana@email.com",
      "12345@Ana"
    )));
  }

  @Test
  public void test_get_user_by_email_success() {
    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

    UserResponse userResponse = userService.getUserByEmail("ana@email.com");

    Assertions.assertNotNull(userResponse);
  }

  @Test
  public void test_get_user_by_email_fail() {
    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

    Assertions.assertThrows(RuntimeException.class, () -> userService.getUserByEmail("email"));
  }

  @Test
  public void test_get_all_by_name_success() {
    Mockito.when(userRepository.findAllByName(Mockito.anyString())).thenReturn(List.of(user));

    List<UserResponse> userResponseList = userService.getAllByName("Ana");

    Assertions.assertNotNull(userResponseList);
    Assertions.assertFalse(userResponseList.isEmpty());
  }

  @Test
  public void test_get_all_by_name_fail() {
    Mockito.when(userRepository.findAllByName(Mockito.anyString())).thenReturn(List.of());

    Assertions.assertThrows(RuntimeException.class, () -> userService.getAllByName("Ana"));
  }

  @Test
  public void test_get_user_by_id_success() {
      Mockito.when(userRepository.findById(Mockito.anyInt()))
        .thenReturn(Optional.of(user));

      UserResponse userResponse = userService.getUserById(1);

      Assertions.assertNotNull(userResponse);
  }

  @Test
  public void test_get_user_by_id_fail() {
      Mockito.when(userRepository.findById(Mockito.anyInt()))
        .thenReturn(Optional.empty());

      Assertions.assertThrows(RuntimeException.class, () -> userService.getUserById(1));
  }

  @Test
  public void test_delete_user_success() {
    Mockito.when(userRepository.findById(Mockito.anyInt()))
      .thenReturn(Optional.of(user));

    userService.deleteUser(1);

    Assertions.assertFalse(user.getActive());
  }

  @Test
  public void test_delete_user_fail() {
    Mockito.when(userRepository.findById(Mockito.anyInt()))
      .thenReturn(Optional.empty());

    Assertions.assertThrows(RuntimeException.class, () -> userService.deleteUser(1));
  }

  @Test
  public void test_update_user_success() {
    User updatedUser = new User();
    Mockito.when(userRepository.findById(Mockito.anyInt()))
      .thenReturn(Optional.of(user));
    Mockito.when(userRepository.save(Mockito.any()))
      .thenReturn(updatedUser);

    UserResponse userResponse = userService.updateUser(1, new UserRequest(
      "newName",
      "newEmail@email.com",
      "newPassword"
    ));

    Assertions.assertNotNull(userResponse);

  }

  @Test
  public void test_update_user_fail() {
    Mockito.when(userRepository.findById(Mockito.anyInt()))
      .thenReturn(Optional.empty());

    Assertions.assertThrows(RuntimeException.class, () -> userService.updateUser(1, new UserRequest(
      "newName",
      "newEmail",
      "newPassword"
    )));
  }
}