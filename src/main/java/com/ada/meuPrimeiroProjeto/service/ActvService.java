/*
package com.ada.MeuPrimeiroProjeto.service;

import com.ada.MeuPrimeiroProjeto.controller.dto.ActvRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.ActvResponse;
import com.ada.MeuPrimeiroProjeto.model.Activities;
import com.ada.MeuPrimeiroProjeto.model.User;
import com.ada.MeuPrimeiroProjeto.repository.ActvRepository;
import com.ada.MeuPrimeiroProjeto.repository.UserRepository;
import com.ada.MeuPrimeiroProjeto.utils.ActvConvert;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ActvService {
  @Autowired
  ActvRepository actvRepository;

  @Autowired
  UserRepository userRepository;


  public ActvResponse saveActv(ActvRequest actvRequest) {
    User user = userRepository.findById(actvRequest.getUserId()).get();

    Activities actv = ActvConvert.toEntity(actvRequest, user);

    return ActvConvert.toResponse(actvRepository.save(actv));
  }

  public List<ActvResponse> getAllByUser(Integer userid) {
    return ActvConvert.toResponseList(actvRepository.findAllByUser(userid));
  }

  public List<ActvResponse> getAllByDate(LocalDate date) {
    return ActvConvert.toResponseList(actvRepository.findAllByDate(date));
  }
}
*/
