package com.casino.authentification.Repository;


import com.casino.authentification.Models.Entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  @Query(
      "SELECT distinct p from Player p WHERE p.email = :email AND p.password = :password"
  )
  Optional<Player> getUserByEmailAndPassword(String email, String password);

  @Query(
      "SELECT distinct p from Player p WHERE p.email = :email"
  )
  Optional<Player> getUserByEmail(String email);

}
