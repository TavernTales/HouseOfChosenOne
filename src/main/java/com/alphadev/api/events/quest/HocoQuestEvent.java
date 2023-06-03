package com.alphadev.api.events.quest;

import com.alphadev.api.HocoAPI;
import com.alphadev.api.events.HocoEvent;
import com.alphadev.entities.HcoQuest;

public class HocoQuestEvent extends HocoEvent {
    private final HcoQuest hcoQuest;
    public HocoQuestEvent(HocoAPI hoco, HcoQuest hcoQuest) {
        super(hoco);
        this.hcoQuest = hcoQuest;
    }

    public HcoQuest getQuest() {
        return hcoQuest;
    }
}
