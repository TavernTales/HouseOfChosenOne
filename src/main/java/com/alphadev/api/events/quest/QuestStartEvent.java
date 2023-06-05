package com.alphadev.api.events.quest;

import com.alphadev.api.IHOCOAPI;
import com.alphadev.entities.HCOPlayer;
import com.alphadev.entities.HCOQuest;
import org.bukkit.inventory.ItemStack;

public class QuestStartEvent extends QuestEvent {
    private final ItemStack bookQuest;
    private final HCOPlayer player;
    public QuestStartEvent(IHOCOAPI hoco, HCOQuest hcoQuest, ItemStack bookQuest, HCOPlayer player) {
        super(hoco, hcoQuest);
        this.bookQuest = bookQuest;
        this.player = player;
    }

    public ItemStack getBookQuest() {
        return bookQuest;
    }

    public HCOPlayer getPlayer() {
        return player;
    }
}
