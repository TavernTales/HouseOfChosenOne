package com.alphadev.events.quest;

import com.alphadev.entity.Quest;
import com.alphadev.services.QuestManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class QuestStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Quest quest;
    private final ItemStack bookQuest;
    private final Player player;
    public QuestStartEvent(Player player){
        super(true);
        this.player = player;
        this.quest = QuestManager.generateTemplateQuest();
        this.quest.setOwner(player.getUniqueId());
        this.bookQuest = QuestManager.createQuestBook(this.quest);
    }

    public Player getPlayer() {
        return player;
    }

    public Quest getQuest() {
        return this.quest;
    }
    public ItemStack getBookQuest() {
        return bookQuest;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
