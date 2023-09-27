package com.ada.MeuPrimeiroProjeto.repository;

import com.ada.MeuPrimeiroProjeto.model.Exercise;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExRepository extends JpaRepository<Exercise, Integer> {

  @Override
  Page<Exercise> findAll(Pageable pageable);

  Optional<Exercise> findByName(String name);

}
