package com.casino.authentification.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogInRequestDTO {
  String email;
  String password;

}
