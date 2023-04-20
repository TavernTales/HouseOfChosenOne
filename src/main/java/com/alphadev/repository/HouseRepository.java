package com.alphadev.repository;

import com.alphadev.entity.House;
import com.alphadev.repository.interfaces.HouseInterfaceRepository;
import com.alphadev.utils.connection.MongoProvider;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;

import java.util.Optional;

public class HouseRepository implements HouseInterfaceRepository {

    private final Datastore datastore = MongoProvider.getInstance().getDatastore();

    @Override
    public Optional<House> findById(long id) {
        return datastore.find(House.class).filter(Filters.eq("id",id)).stream().findFirst();
    }

    @Override
    public Optional<House> findByName(String name) {
        return datastore.find(House.class).filter(Filters.eq("name", name)).stream().findFirst();
    }

    @Override
    public House save(House house) {
        return datastore.save(house);
    }
}
