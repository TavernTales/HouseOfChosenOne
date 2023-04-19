package com.alphadev.events;

import com.alphadev.entity.Quest;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class QuestsOnProgressEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Quest quest;

    public QuestsOnProgressEvent(Quest quest) {
        this.quest = quest;
    }
    public QuestsOnProgressEvent(final boolean async, Quest quest) {
        super(async);
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }


    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}