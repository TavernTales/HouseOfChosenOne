package com.alphadev.events;

import com.alphadev.services.PlayerMoveService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEvents implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        PlayerMoveService.trackPlayerMove(event.getPlayer());
    }
}
