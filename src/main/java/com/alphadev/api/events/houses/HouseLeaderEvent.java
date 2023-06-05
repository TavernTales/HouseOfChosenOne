package com.alphadev.api.events.houses;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.api.entities.IHOCOHouse;
import com.alphadev.api.entities.IHOCOPlayer;

public class HouseLeaderEvent extends HouseEvent {
    private final IHOCOPlayer leader;
    public HouseLeaderEvent(IHOCOAPI hoco, IHOCOHouse house, IHOCOPlayer leader) {
        super(hoco, house);
        this.leader = leader;
    }

    public IHOCOPlayer getLeader() {
        return leader;
    }
}
