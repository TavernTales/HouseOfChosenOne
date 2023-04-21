package com.alphadev.repository;

import com.alphadev.entity.House;
import com.alphadev.enums.HouseEnum;
import com.alphadev.repository.interfaces.HouseInterfaceRepository;
import com.alphadev.utils.connection.MongoProvider;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;

import java.util.Arrays;
import java.util.Optional;

public class HouseRepository implements HouseInterfaceRepository {

    private final Datastore datastore = MongoProvider.getInstance().getDatastore();

    @Override
    public Optional<House> findById(long id) {
        return datastore.find(House.class).filter(Filters.eq("id",id)).stream().findFirst();
    }

    @Override
    public Optional<House> findByName(String name) {
        Long id = Long.valueOf(Arrays.stream(HouseEnum.values()).filter(houseEnum -> houseEnum.getName().equalsIgnoreCase(name)).findFirst().get().getId());
        return datastore.find(House.class).filter(Filters.eq("id",id)).stream().findFirst();
    }

    @Override
    public House save(House house) {
        return datastore.save(house);
    }
}
