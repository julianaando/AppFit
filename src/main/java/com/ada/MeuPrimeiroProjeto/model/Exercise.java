package com.ada.MeuPrimeiroProjeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "reps", nullable = false)
  private String reps;

  @Column(name = "weight")
  private Double weight;

  @Column(name = "sets", nullable = false)
  private Integer sets;

  @Column(name = "rest", nullable = false)
  private Integer rest;

  @ManyToOne
  private User user;
}
