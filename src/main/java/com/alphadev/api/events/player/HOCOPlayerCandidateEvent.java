package com.alphadev.api.events.player;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.api.entities.IHOCOPlayer;

public class HOCOPlayerCandidateEvent extends HOCOPlayerEvent {
    private final int votes;
    public HOCOPlayerCandidateEvent(IHOCOAPI hoco, IHOCOPlayer player, int votes) {
        super(hoco, player);
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }
}
