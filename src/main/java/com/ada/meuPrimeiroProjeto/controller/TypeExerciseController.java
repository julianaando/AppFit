package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExerciseResponse;
import com.ada.meuPrimeiroProjeto.service.TypeExerciseService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/type-exercise")
public class TypeExerciseController {

  private final TypeExerciseService typeExerciseService;

  @Autowired
  public TypeExerciseController(TypeExerciseService typeExerciseService){
    this.typeExerciseService = typeExerciseService;
  }

  @GetMapping
  public ResponseEntity<List<TypeExerciseResponse>> getAllTypeExercise(){
    return ResponseEntity.ok(typeExerciseService.getAllTypeExercises());
  }

  @PostMapping
  public ResponseEntity<TypeExerciseResponse> saveTypeExercise(
    @RequestBody TypeExerciseRequest typeExerciseRequest
  ){
    TypeExerciseResponse typeExerciseResponse = typeExerciseService.saveTypeExercise(typeExerciseRequest);
    return ResponseEntity.created(
        URI.create("/type-exercise/"+ typeExerciseResponse.getId())
    ).body(typeExerciseResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TypeExerciseResponse> updateTypeExercise(@PathVariable Integer id, @RequestBody TypeExerciseRequest typeExerciseRequest){
    TypeExerciseResponse typeExerciseResponse = typeExerciseService.updateTypeExercise(id, typeExerciseRequest);
    return ResponseEntity.ok(typeExerciseResponse);
  }

  @DeleteMapping("/{id}")
  public void deleteTypeProduct(@PathVariable Integer id){
    typeExerciseService.deleteTypeExercise(id);
  }
}
