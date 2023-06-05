package com.alphadev.api.events.quest;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.entities.HCOQuest;
import org.bukkit.entity.Player;

public class QuestEndEvent extends QuestEvent {
    private final Player player;
    public QuestEndEvent(IHOCOAPI hoco, HCOQuest hcoQuest, Player player) {
        super(hoco, hcoQuest);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}