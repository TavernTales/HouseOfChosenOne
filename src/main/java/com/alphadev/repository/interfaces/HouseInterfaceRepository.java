package com.alphadev.repository.interfaces;

import com.alphadev.entity.House;

import java.util.List;
import java.util.Optional;

public interface HouseInterfaceRepository {

    boolean isEmpty();

    Optional<House> findById(long id);

    void saveAll(List<House> house);

    List<House> getAll();
}
