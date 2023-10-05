package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.TypeExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.TypeExResponse;
import com.ada.meuPrimeiroProjeto.service.TypeExService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-exercise")
public class TypeExController {

  @Autowired
  TypeExService typeExService;

  @GetMapping
  public ResponseEntity<List<TypeExResponse>> getAllTypeExercise(){
    return ResponseEntity.ok(typeExService.getAllTypeExercises());
  }

  @PostMapping
  public ResponseEntity<TypeExResponse> saveTypeExercise(@RequestBody TypeExRequest typeExRequest){
    TypeExResponse typeExResponse = typeExService.saveTypeExercise(typeExRequest);
    return ResponseEntity.created(
        URI.create("/type-exercise/"+typeExResponse.getId())
    ).body(typeExResponse);
  }

  @DeleteMapping("/{id}")
  public void deleteTypeProduct(@PathVariable Integer id){
    typeExService.deleteTypeExercise(id);
  }
}
