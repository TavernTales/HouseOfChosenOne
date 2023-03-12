package com.alphadev.events;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigPlayers;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;


public class PlayerDieEvents implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDieEvent(PlayerRespawnEvent event){

        Player player = event.getPlayer();

        if(player.getBedSpawnLocation() != null)
            return;

        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection != null && configurationSection.getString("house") != null){
            House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));
            event.setRespawnLocation(house.getLocation());
            player.sendTitle(ChatColorUtil.boldText(house.getHouse()),"",10,20,10);
        }
    }



}
