package com.alphadev.events;

import com.alphadev.services.ScoreBoardService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEvents implements Listener {


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        ScoreBoardService.setPlayerHouseScoreBoardTag(event.getPlayer());
    }
}
