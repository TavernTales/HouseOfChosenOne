package com.alphadev.api.events.quest;

import com.alphadev.api.HocoAPI;
import com.alphadev.entities.HcoQuest;
import org.bukkit.entity.Player;

public class HocoQuestEndEvent extends HocoQuestEvent{
    private final Player player;
    public HocoQuestEndEvent(HocoAPI hoco, HcoQuest hcoQuest, Player player) {
        super(hoco, hcoQuest);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}