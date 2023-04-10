package com.alphadev.events;

import com.alphadev.services.SignHouseService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractEventListener implements Listener {

    @EventHandler
    public void signHouseInteration(PlayerInteractEvent event){
        SignHouseService.signHouseInteract(event);
    }


    @EventHandler
    public void  blockBrea(BlockBreakEvent event){
    }
}
