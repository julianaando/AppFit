package com.ada.meuPrimeiroProjeto.repository;

import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeExerciseRepository extends JpaRepository<TypeExercise, Integer> {
}
