package com.ada.meuPrimeiroProjeto.controller;

import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesRequest;
import com.ada.meuPrimeiroProjeto.controller.dto.ActivitiesResponse;
import com.ada.meuPrimeiroProjeto.service.ActivitiesService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

  @Autowired
  ActivitiesService activitiesService;

  @PostMapping
  public ResponseEntity<ActivitiesResponse> saveActivities(@RequestBody ActivitiesRequest activitiesRequest) {
    ActivitiesResponse activitiesResponse = activitiesService.saveActivities(activitiesRequest);
    return ResponseEntity.created(URI.create("/activities/" + activitiesResponse.getId()))
      .body(activitiesResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ActivitiesResponse> getActivitiesById(@PathVariable Integer id) {
    return ResponseEntity.ok(activitiesService.getActivitiesById(id));
  }

  @GetMapping
  public ResponseEntity<List<ActivitiesResponse>> getAllActivities(
    @RequestParam(name = "userId", required = false) Integer userId
  ) {
    return ResponseEntity.ok(activitiesService.getAllActivities(userId));
  }

}
