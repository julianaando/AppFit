package com.ada.meuPrimeiroProjeto.repository;

import com.ada.meuPrimeiroProjeto.model.Exercise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExRepository extends JpaRepository<Exercise, Integer> {

  @Query("SELECT e FROM Exercise e WHERE e.type.id = :type")
  List<Exercise> findExerciseByType(@Param("type") Integer typeExercise);

  @Query("SELECT d FROM Exercise d WHERE d.date = :date")
  List<Exercise> findExerciseByDate(@Param("date") String date);
}
