/*
package com.ada.MeuPrimeiroProjeto.controller;

import com.ada.MeuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.MeuPrimeiroProjeto.model.Activities;
import com.ada.MeuPrimeiroProjeto.service.ActvService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActvController {

  @Autowired
  ActvService actvService;

  @PostMapping
  public ActvResponse saveActv(@RequestBody ActvRequest actvRequest){
    return actvService.saveActv(actvRequest);
  }

  @GetMapping
  public List<ActvResponse> getActv(
    @RequestParam(name = "userId", required = false) Integer userId,
    @RequestParam(name = "date", required = false) LocalDate date
  ){
    if (userId != null) {
      return actvService.getAllByUser(userId);
    } else if (date != null) {
      return actvService.getAllByDate(date);
    } else {
      return null;
    }
  }

}
*/
