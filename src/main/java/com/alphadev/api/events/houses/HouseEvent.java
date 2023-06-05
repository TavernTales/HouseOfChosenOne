package com.alphadev.api.events.houses;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.api.entities.IHOCOHouse;
import com.alphadev.api.events.HOCOEvent;

public class HouseEvent extends HOCOEvent {
    private final IHOCOHouse house;
    public HouseEvent(IHOCOAPI hoco, IHOCOHouse house) {
        super(hoco);
        this.house = house;
    }

    public IHOCOHouse getHouse() {
        return house;
    }
}
