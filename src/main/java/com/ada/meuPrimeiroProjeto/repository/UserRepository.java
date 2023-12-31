package com.ada.meuPrimeiroProjeto.repository;

import java.util.List;
import com.ada.meuPrimeiroProjeto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    @Query(value = "SELECT * FROM USERS WHERE ACTIVE = TRUE", nativeQuery = true)
    List<User> findAll();
    User findByEmail(String email);
    List<User> findAllByName(String name);

}
