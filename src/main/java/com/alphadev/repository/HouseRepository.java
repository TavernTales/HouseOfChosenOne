package com.alphadev.repository;

import com.alphadev.entity.House;
import com.alphadev.repository.interfaces.HouseInterfaceRepository;
import com.alphadev.utils.connection.MongoProvider;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;

import java.util.List;
import java.util.Optional;

public class HouseRepository implements HouseInterfaceRepository {

    private final Datastore datastore = MongoProvider.getInstance().getDatastore();
    @Override
    public boolean countHouses(){
        return datastore.find(House.class).count() >=0;
    }
    @Override
    public Optional<House> findById(long id) {
        return datastore.find(House.class).filter(Filters.eq("id",id)).stream().findFirst();
    }

    @Override
    public void saveAll(List<House> house) {
        datastore.save(house);
    }

    @Override
    public List<House> getAll() {
        Query<House> query = datastore.find(House.class);
        return query.iterator().toList();
    }
}
