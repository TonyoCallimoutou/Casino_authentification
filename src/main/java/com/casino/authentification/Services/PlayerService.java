package com.casino.authentification.Services;

import com.casino.authentification.Models.DTO.LogInRequestDTO;
import com.casino.authentification.Models.DTO.SignUpRequestDTO;

public interface PlayerService {

  String logIn(LogInRequestDTO logInRequest);
  String signUp(SignUpRequestDTO logInRequest);
}
