package com.casino.authentification.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequestDTO {
  String username;
  String email;
  String password;
}
