package com.alphadev.repository.interfaces;

import com.alphadev.entity.House;

import java.util.Optional;

public interface HouseInterfaceRepository {

    Optional<House> findById(long id);
    House save(House house);

}
