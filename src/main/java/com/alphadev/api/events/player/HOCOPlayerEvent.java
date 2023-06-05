package com.alphadev.api.events.player;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.api.entities.IHOCOPlayer;
import com.alphadev.api.events.HOCOEvent;

public class HOCOPlayerEvent extends HOCOEvent {
    private final IHOCOPlayer player;
    public HOCOPlayerEvent(IHOCOAPI hoco, IHOCOPlayer player) {
        super(hoco);
        this.player = player;
    }

    public IHOCOPlayer getPlayer() {
        return player;
    }
}
