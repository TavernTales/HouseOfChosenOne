package com.alphadev.api.events.player;

import com.alphadev.api.HocoAPI;
import com.alphadev.api.entities.HocoPlayer;
import com.alphadev.api.events.HocoEvent;

public class HocoPlayerEvent extends HocoEvent {
    private final HocoPlayer player;
    public HocoPlayerEvent(HocoAPI hoco, HocoPlayer player) {
        super(hoco);
        this.player = player;
    }

    public HocoPlayer getPlayer() {
        return player;
    }
}
