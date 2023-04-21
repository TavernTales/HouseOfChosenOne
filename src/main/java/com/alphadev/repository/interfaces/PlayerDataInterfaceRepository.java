package com.alphadev.repository.interfaces;

import com.alphadev.entity.PlayerData;

import java.util.Optional;
import java.util.UUID;

public interface PlayerDataInterfaceRepository {

    Optional<PlayerData> findById(UUID uuid);
    PlayerData save(PlayerData playerData);

}
