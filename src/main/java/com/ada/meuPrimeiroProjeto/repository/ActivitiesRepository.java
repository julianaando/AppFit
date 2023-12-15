package com.ada.meuPrimeiroProjeto.repository;

import com.ada.meuPrimeiroProjeto.model.Activities;
import com.ada.meuPrimeiroProjeto.model.Exercise;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, Integer> {

  @Query(value = "SELECT * FROM ACTIVITIES WHERE USER_ID = :userId", nativeQuery = true)
  List<Activities> findAllByUser(Integer userId);
}
