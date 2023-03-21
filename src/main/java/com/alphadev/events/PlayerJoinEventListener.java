package com.alphadev.events;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.services.ScoreBoardService;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        Bukkit.getScheduler().scheduleSyncDelayedTask(HouseOfChosenOne.getPlugin(),() -> ScoreBoardService.setPlayerHouseScoreBoardTag(event.getPlayer()),100);
    }
}
