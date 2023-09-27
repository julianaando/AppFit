package com.ada.MeuPrimeiroProjeto.repository;

import java.util.List;
import java.util.Optional;
import com.ada.MeuPrimeiroProjeto.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);
    List<User> findAllByName(String name);

}
