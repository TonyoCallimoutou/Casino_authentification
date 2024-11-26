package com.casino.authentification.Models.Entities;

import com.casino.authentification.Models.Enums.PlayerRoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String username;
  private String email;
  private String password;

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PlayerHasRoles> listPlayerHasRoles = new ArrayList<>();

  // Constructeur
  public Player(PlayerRoleEnum role) {
    this.addRole(role);
  }

  public Player() {
    this(PlayerRoleEnum.ROLE_PLAYER);
  }

  // Méthode pour ajouter un rôle
  public void addRole(PlayerRoleEnum role) {
    PlayerHasRoles playerRole = new PlayerHasRoles();
    playerRole.setPlayer(this);
    playerRole.setRoles(role);
    this.listPlayerHasRoles.add(playerRole);
  }

}
