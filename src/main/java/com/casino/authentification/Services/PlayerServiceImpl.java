package com.casino.authentification.Services;

import com.casino.authentification.Models.DTO.LogInRequestDTO;
import com.casino.authentification.Models.DTO.SignUpRequestDTO;
import com.casino.authentification.Models.Entities.Player;
import com.casino.authentification.Models.Mapper.PlayerMapper;
import com.casino.authentification.Repository.PlayerRepository;
import com.casino.authentification.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;
  private final AuthenticationManager authenticationManager;
  @Autowired
  private final PlayerMapper mapper;

  @Autowired
  public PlayerServiceImpl(PlayerRepository playerRepository, AuthenticationManager authenticationManager, PlayerMapper mapper) {
    this.playerRepository = playerRepository;
    this.authenticationManager = authenticationManager;
    this.mapper = mapper;
  }

  @Override
  public String logIn(LogInRequestDTO logInRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            logInRequest.getEmail(),
            logInRequest.getPassword()
        )
    );

    // Récupérer le principal (utilisateur authentifié) et générer un jeton
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return JwtUtils.generateToken(userDetails.getUsername());
  }

  @Override
  public String signUp(SignUpRequestDTO signUpRequestDTO) {
    // Vérifier si l'utilisateur existe déjà
    if (playerRepository.getUserByEmail(signUpRequestDTO.getEmail()).isPresent()) {
      throw new IllegalStateException("Email already in use");
    }

    Player newPlayer = mapper.dtoToEntity(signUpRequestDTO);
    JwtUtils.encodePassword(newPlayer);

    playerRepository.save(newPlayer);

    return JwtUtils.generateToken(newPlayer.getUsername());
  }
}
