package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExerciseResponse;
import com.ada.meuPrimeiroProjeto.model.TypeExercise;
import com.ada.meuPrimeiroProjeto.service.ExerciseService;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/exercise")
public class ExerciseController {

  @Autowired
  ExerciseService exerciseService;

  @GetMapping
  public ResponseEntity getAllExercises(
      @RequestParam(name = "typeExercise", required = false) Integer typeExercise
  ){
    try {
      return ResponseEntity.ok(exerciseService.getAllExercise(typeExercise));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Não foi possível listar os exercícios!");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity getExerciseById(@PathVariable Integer id){
    try {
      return ResponseEntity.ok(exerciseService.getExerciseById(id));
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body("Exercício não encontrado!");
    }
  }

  @PostMapping
  public ResponseEntity saveExercise(@RequestBody ExerciseRequest exerciseRequest) {
    try {
      ExerciseResponse exerciseResponse = exerciseService.saveExercise(exerciseRequest);
      return ResponseEntity.created(URI.create("/exercise/"+ exerciseResponse.getId())).body(
          exerciseResponse);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Não foi possível registrar o exercício!");
    }
  }

  @GetMapping("/date/{date}")
  public ResponseEntity getAllByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
    try {
      return ResponseEntity.ok(exerciseService.getAllByDate(date));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Não há exercício registrado nesta data!");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteExercise(@PathVariable Integer id){
    try {
      exerciseService.deleteExercise(id);
      return ResponseEntity.ok().body("Exercício deletado com sucesso!");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Não foi possível deletar o exercício!");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity updateExercise(
      @PathVariable Integer id,
      @RequestBody ExerciseRequest exerciseRequest, TypeExercise typeExercise
  ){
    try {
    ExerciseResponse exerciseResponse = exerciseService.updateExercise(id, exerciseRequest, typeExercise);
    return ResponseEntity.ok(exerciseResponse);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Não foi possível atualizar o exercício!");
    }
  }
}
