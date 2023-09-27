package com.ada.MeuPrimeiroProjeto.repository;

import com.ada.MeuPrimeiroProjeto.model.Activities;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActvRepository extends JpaRepository<Activities, Integer> {

  @Override
  Page<Activities> findAll(Pageable pageable);
/*  Optional<Activities> findByExercise(String exercise);*/

  List<Activities> findAllByUser(Integer userid);
  List<Activities> findAllByDate(LocalDate date);
}
