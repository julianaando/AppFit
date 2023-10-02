package com.ada.MeuPrimeiroProjeto.repository;

import com.ada.MeuPrimeiroProjeto.model.Exercise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExRepository extends JpaRepository<Exercise, Integer> {

  @Query("SELECT e FROM Exercise e WHERE e.type.id = :type")
  List<Exercise> findExerciseByType(@Param("type") Integer typeExercise);

}
