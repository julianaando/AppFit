package com.ada.MeuPrimeiroProjeto.repository;

import com.ada.MeuPrimeiroProjeto.model.TypeExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeExRepository extends JpaRepository<TypeExercise, Integer> {
}
