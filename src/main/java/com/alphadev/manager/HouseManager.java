package com.alphadev.manager;

import com.alphadev.api.entities.IHOCOPlayer;
import com.alphadev.api.manager.IHouseManager;
import com.alphadev.database.repositories.HouseRepository;
import com.alphadev.entities.HCOHouse;
import com.alphadev.entities.HCOPlayer;
import com.alphadev.enums.HouseRelationshipEnum;
import org.bukkit.Location;

import java.util.List;


public class HouseManager implements IHouseManager {
    private HouseRepository repository;
    public HouseManager() {
    }

    public void setNewLocation(HCOHouse house, Location location) {

    }
    public HCOPlayer getPlayerLeader(HCOHouse house) {
        return null;
    }
    public HouseRelationshipEnum getHouseRelationships(HCOHouse house) {
        return null;
    }

    public HouseRelationshipEnum getHouseRelationshipBetween(HCOHouse house, HCOHouse anotherHouse) {
        return null;
    }

    public List<? extends IHOCOPlayer> getAllPlayersHouse(HCOHouse house) {
        return null;
    }

    public List<? extends IHOCOPlayer> getAllCandidates(HCOHouse house) {
        return null;
    }
}
