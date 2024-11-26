package com.casino.authentification.Controllers;

import com.casino.authentification.Models.DTO.LogInRequestDTO;
import com.casino.authentification.Models.DTO.SignUpRequestDTO;
import com.casino.authentification.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private final PlayerService service;

  public AuthController(PlayerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello from auth");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LogInRequestDTO logInRequest) {
    String token = this.service.logIn(logInRequest);
    return ResponseEntity.ok(token);
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
    String token = this.service.signUp(signUpRequestDTO);
    return ResponseEntity.ok(token);
  }
}