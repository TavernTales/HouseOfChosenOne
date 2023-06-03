package com.alphadev.api.events.houses;

import com.alphadev.api.HocoAPI;
import com.alphadev.api.entities.HocoHouse;
import com.alphadev.api.events.HocoEvent;

public class HocoHouseEvent extends HocoEvent {
    private final HocoHouse house;
    public HocoHouseEvent(HocoAPI hoco, HocoHouse house) {
        super(hoco);
        this.house = house;
    }

    public HocoHouse getHouse() {
        return house;
    }
}
