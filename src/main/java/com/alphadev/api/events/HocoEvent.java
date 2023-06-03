package com.alphadev.api.events;

import com.alphadev.api.HocoAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class HocoEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final HocoAPI hoco;

    public HocoEvent(HocoAPI hoco) {
        this.hoco = hoco;
    }

    public HocoAPI getHoco() {
        return this.hoco;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}