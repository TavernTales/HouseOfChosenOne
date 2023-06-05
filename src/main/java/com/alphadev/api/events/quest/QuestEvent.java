package com.alphadev.api.events.quest;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.api.events.HOCOEvent;
import com.alphadev.entities.HCOQuest;

public class QuestEvent extends HOCOEvent {
    private final HCOQuest hcoQuest;
    public QuestEvent(IHOCOAPI hoco, HCOQuest hcoQuest) {
        super(hoco);
        this.hcoQuest = hcoQuest;
    }

    public HCOQuest getQuest() {
        return hcoQuest;
    }
}
