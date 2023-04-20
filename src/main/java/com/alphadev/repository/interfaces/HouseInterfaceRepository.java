package com.alphadev.repository.interfaces;

import com.alphadev.entity.House;

import java.util.Optional;

public interface HouseInterfaceRepository {

    Optional<House> findById(long id);
    Optional<House> findByName(String name);
    House save(House house);

}
