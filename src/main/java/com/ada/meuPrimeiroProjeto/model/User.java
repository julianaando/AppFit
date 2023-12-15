package com.ada.meuPrimeiroProjeto.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
@Where(clause = "active is true")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  private Boolean active;


}
