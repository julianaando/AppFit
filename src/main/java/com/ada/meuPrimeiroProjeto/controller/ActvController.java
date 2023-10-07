package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.meuPrimeiroProjeto.service.ActvService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActvController {

  @Autowired
  ActvService actvService;

  @PostMapping
  public ResponseEntity<ActvResponse> saveActv(@RequestBody ActvRequest actvRequest) {
    ActvResponse actvResponse = actvService.saveActv(actvRequest);
    return ResponseEntity.created(URI.create("/activities/" + actvResponse.getId()))
      .body(actvResponse);
  }

  @GetMapping
  public ResponseEntity<List<ActvResponse>> getActv(
    @RequestParam(name = "userId", required = false) Integer userId,
    @RequestParam(name = "exerciseId", required = false) Integer exerciseId
  ) {
    return ResponseEntity.ok(actvService.getAllActivities(userId, exerciseId));
  }

}
