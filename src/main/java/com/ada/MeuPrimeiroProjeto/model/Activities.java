package com.ada.MeuPrimeiroProjeto.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "activities")
public class Activities {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "exercise", nullable = false)
  private String exercise;

  @Column(name = "reps", nullable = false)
  private String reps;

  @Column(name = "weight", nullable = false)
  private Double weight;

  @Column(name = "sets", nullable = false)
  private Integer sets;

  @Column(name = "rest", nullable = false)
  private Integer rest;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @ManyToOne
  private User user;
}
