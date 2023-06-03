package com.alphadev.manager;

import com.alphadev.Hoco;
import com.alphadev.api.entities.HocoPlayer;
import com.alphadev.api.manager.HocoHouseManager;
import com.alphadev.database.repositories.HcoHouseRepository;
import com.alphadev.entities.HcoHouse;
import com.alphadev.entities.HcoPlayer;
import com.alphadev.enums.HouseRelationshipEnum;
import org.bukkit.Location;

import java.util.List;


public class HouseManager implements HocoHouseManager {
    private final Hoco hoco;
    private HcoHouseRepository repository;
    public HouseManager(Hoco hoco) {
        this.hoco = hoco;
    }

    public void setNewLocation(HcoHouse house, Location location) {

    }
    public HcoPlayer getPlayerLeader(HcoHouse house) {
        return null;
    }
    public HouseRelationshipEnum getHouseRelationships(HcoHouse house) {
        return null;
    }

    public HouseRelationshipEnum getHouseRelationshipBetween(HcoHouse house, HcoHouse anotherHouse) {
        return null;
    }

    public List<? extends HocoPlayer> getAllPlayersHouse(HcoHouse house) {
        return null;
    }

    public List<? extends HocoPlayer> getAllCandidates(HcoHouse house) {
        return null;
    }
}
