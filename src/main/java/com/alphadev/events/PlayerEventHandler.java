package com.alphadev.events;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.services.ChatManagerService;
import com.alphadev.services.PlayerMoveService;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.services.SignHouseService;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerEventHandler implements Listener {
    @EventHandler
    public void signHouseInteration(PlayerInteractEvent event){
        SignHouseService.signHouseInteract(event);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        ChatManagerService.onPlayerQuitEvent(event);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        ChatManagerService.onPlayerJoinEvent(event);
    }


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        Bukkit.getScheduler().scheduleSyncDelayedTask(HouseOfChosenOne.getPlugin(),() -> ScoreBoardService.setPlayerHouseScoreBoardTag(event.getPlayer()),100);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        PlayerMoveService.trackPlayerMove(event.getPlayer());
    }


    @EventHandler
    public void onPlayerReceiveMessage(AsyncPlayerChatEvent event){
        ChatManagerService.onPlayerReceiveMessages(event);
    }


}
