package com.alphadev.events;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.services.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
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


    @EventHandler
    public  void  onPlayerInvetoryClickEvent(InventoryClickEvent event){
        WayStoneService.toggleWayStoneOption(event);
    }


    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event){
      //  WayStoneService.placePersonalWaystone(event);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractAtEntityEvent event){
        WayStoneService.interactWithPersonalWayStone(event);
    }

}
