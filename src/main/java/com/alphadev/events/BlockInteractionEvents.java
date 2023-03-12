package com.alphadev.events;

import com.alphadev.services.SignHouseService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractionEvents implements Listener {

    @EventHandler
    public void signHouseInteration(PlayerInteractEvent event){
        SignHouseService.signHouseInteract(event);
    }

}
