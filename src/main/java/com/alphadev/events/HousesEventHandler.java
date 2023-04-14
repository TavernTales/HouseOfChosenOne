package com.alphadev.events;


import com.alphadev.services.HouseService;
import com.alphadev.services.SignHouseService;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HousesEventHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawnAndHasHouse(PlayerRespawnEvent event){
        new HouseService().onPlayerRespawn(event.getPlayer(), event);
    }

    @EventHandler
    public void onSignPlaceEvent(SignChangeEvent event){
        SignHouseService.signPlaceHouseSetup(event);
    }
}
