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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

  private final ExerciseService exerciseService;

  @Autowired
  public ExerciseController(ExerciseService exerciseService) {
    this.exerciseService = exerciseService;
  }

  @GetMapping
  public ResponseEntity<List<ExerciseResponse>> getAllExercises() {
    return ResponseEntity.ok(exerciseService.getAllExercises());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExerciseResponse> getExerciseById(@PathVariable Integer id) {
    return ResponseEntity.ok(exerciseService.getExerciseById(id));
  }

  @PostMapping
  public ResponseEntity<ExerciseResponse> saveExercise(
    @RequestBody ExerciseRequest exerciseRequest
  ) {
    ExerciseResponse exerciseResponse = exerciseService.saveExercise(exerciseRequest);
    return ResponseEntity.created(URI.create("/exercise/" + exerciseResponse.getId())).body(exerciseResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteExercise(@PathVariable Integer id) {
    exerciseService.deleteExercise(id);
    return ResponseEntity.ok().body("Exercício deletado com sucesso!");
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExerciseResponse>  updateExercise(
    @PathVariable Integer id,
    @RequestBody ExerciseRequest exerciseRequest
  ) {
    ExerciseResponse exerciseResponse = exerciseService.updateExercise(id, exerciseRequest);
    return ResponseEntity.ok(exerciseResponse);
  }
}
