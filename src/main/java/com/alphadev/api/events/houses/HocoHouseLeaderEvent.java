package com.alphadev.api.events.houses;

import com.alphadev.api.HocoAPI;
import com.alphadev.api.entities.HocoHouse;
import com.alphadev.api.entities.HocoPlayer;

public class HocoHouseLeaderEvent extends HocoHouseEvent{
    private final HocoPlayer leader;
    public HocoHouseLeaderEvent(HocoAPI hoco, HocoHouse house, HocoPlayer leader) {
        super(hoco, house);
        this.leader = leader;
    }

    public HocoPlayer getLeader() {
        return leader;
    }
}
