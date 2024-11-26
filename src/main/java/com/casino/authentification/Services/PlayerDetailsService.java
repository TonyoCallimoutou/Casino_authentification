package com.casino.authentification.Services;

import com.casino.authentification.Models.Entities.Player;
import com.casino.authentification.Repository.PlayerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlayerDetailsService implements UserDetailsService {

  private final PlayerRepository playerRepository;

  public PlayerDetailsService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Player player = playerRepository.getUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    // Transformer le Player en un objet UserDetails pour Spring Security
    return new org.springframework.security.core.userdetails.User(
        player.getEmail(),
        player.getPassword(),
        player.getListPlayerHasRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoles().toString()))
            .collect(Collectors.toList())
    );
  }
}