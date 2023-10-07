package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ExRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ExResponse;
import com.ada.meuPrimeiroProjeto.service.ExService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/exercise")
public class ExController {

  @Autowired
  ExService exService;

  @GetMapping
  public ResponseEntity<List<ExResponse>> getAllExercises(
      @RequestParam(name = "typeExercise", required = false) Integer typeExercise
  ){
    return ResponseEntity.ok(exService.getAllExercise(typeExercise));
  }
  @PostMapping
  public ResponseEntity<ExResponse> saveExercise(@RequestBody ExRequest exRequest) {
    ExResponse exResponse = exService.saveExercise(exRequest);

    return ResponseEntity.created(URI.create("/exercise/"+exResponse.getId())).body(exResponse);
  }

  @GetMapping("/date/{date}")
  public ResponseEntity<List<ExResponse>> getAllByDate(@RequestParam String date){
    return ResponseEntity.ok(exService.getAllByDate(date));
  }

}
