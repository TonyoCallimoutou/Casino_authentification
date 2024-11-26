package com.casino.authentification.Models.Mapper;

import com.casino.authentification.Models.DTO.SignUpRequestDTO;
import com.casino.authentification.Models.Entities.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

  SignUpRequestDTO entityToDTO(Player player);
  Player dtoToEntity(SignUpRequestDTO playerDTO);

  @Mapping(target = "password", source = "password")
  @Mapping(target = "listPlayerHasRoles", ignore = true)
  Player authenticateToEntity(Player player, String password);
}
