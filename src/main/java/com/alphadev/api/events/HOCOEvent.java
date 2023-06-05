package com.alphadev.api.events;

import com.alphadev.api.IHOCOAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class HOCOEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final IHOCOAPI hoco;

    public HOCOEvent(IHOCOAPI hoco) {
        this.hoco = hoco;
    }

    public IHOCOAPI getHoco() {
        return this.hoco;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}