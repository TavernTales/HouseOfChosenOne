package com.alphadev.api.events.quest;

import com.alphadev.api.HocoAPI;
import com.alphadev.entities.HcoPlayer;
import com.alphadev.entities.HcoQuest;
import org.bukkit.inventory.ItemStack;

public class HocoQuestStartEvent extends HocoQuestEvent{
    private final ItemStack bookQuest;
    private final HcoPlayer player;
    public HocoQuestStartEvent(HocoAPI hoco, HcoQuest hcoQuest, ItemStack bookQuest, HcoPlayer player) {
        super(hoco, hcoQuest);
        this.bookQuest = bookQuest;
        this.player = player;
    }

    public ItemStack getBookQuest() {
        return bookQuest;
    }

    public HcoPlayer getPlayer() {
        return player;
    }
}
