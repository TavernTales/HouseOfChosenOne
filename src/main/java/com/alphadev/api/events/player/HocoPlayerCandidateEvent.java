package com.alphadev.api.events.player;

import com.alphadev.api.HocoAPI;
import com.alphadev.api.entities.HocoPlayer;

public class HocoPlayerCandidateEvent extends HocoPlayerEvent{
    private final int votes;
    public HocoPlayerCandidateEvent(HocoAPI hoco, HocoPlayer player, int votes) {
        super(hoco, player);
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }
}
