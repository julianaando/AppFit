package com.ada.meuPrimeiroProjeto.repository;

import com.ada.meuPrimeiroProjeto.model.Exercise;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
  @Override
  @Query(value = "SELECT * FROM EXERCISES WHERE ACTIVE = TRUE", nativeQuery = true)
  List<Exercise> findAll();
}
