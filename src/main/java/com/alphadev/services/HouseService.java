package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigPlayers;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HouseService {

    public void onPlayerRespawn(Player player, PlayerRespawnEvent event){

        if(player.getBedSpawnLocation() != null)
            return;

        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection != null && configurationSection.getString("house") != null){
            House house = new House( HouseOfChosenOne.getConfigFile(),configurationSection.getString("house"));

            if (house.getLocation() == null)
                return;

            event.setRespawnLocation(house.getLocation());
            player.sendTitle(ChatColorUtil.boldText(house.getName()),"",10,20,10);
        }
    }




}
