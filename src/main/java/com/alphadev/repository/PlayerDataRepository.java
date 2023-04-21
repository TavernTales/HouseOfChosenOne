package com.alphadev.repository;

import com.alphadev.entity.PlayerData;
import com.alphadev.repository.interfaces.PlayerDataInterfaceRepository;
import com.alphadev.utils.connection.MongoProvider;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;

import java.util.Optional;
import java.util.UUID;

public class PlayerDataRepository implements PlayerDataInterfaceRepository {
    private final Datastore datastore = MongoProvider.getInstance().getDatastore();

    @Override
    public Optional<PlayerData> findById(UUID uuid) {
        return datastore.find(PlayerData.class).filter(Filters.eq("uuid",uuid)).stream().findFirst();
    }

    @Override
    public PlayerData save(PlayerData playerData) {
        return datastore.save(playerData);
    }
}
