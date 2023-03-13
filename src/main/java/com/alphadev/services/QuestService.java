package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.ItemFactoryUtil;
import com.alphadev.utils.config.ConfigPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class QuestService {

    public void buildDailyQuest(Player player){
        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection == null || configurationSection.getString("house") == null)
            return;

        House house = new House(HouseOfChosenOne.getConfigFile() ,configurationSection.getString("house"));
        Inventory inventory = Bukkit.createInventory( player, InventoryType.PLAYER, ChatColorUtil.boldText("Gerenciador de Miss\u00F5es", ChatColor.DARK_AQUA));
        inventory.addItem(ItemFactoryUtil.questCreate());

        player.openInventory(inventory);
    }

    public void openQuestMenu(Player player){
        ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
        if(configurationSection == null || configurationSection.getString("house") == null)
            return;

        House house = new House(HouseOfChosenOne.getConfigFile() ,configurationSection.getString("house"));

        player.openInventory(Bukkit.createInventory( player, InventoryType.PLAYER, ChatColorUtil.boldText("Miss\u00F5es: ", ChatColor.DARK_AQUA)+house.getHouse() ));
    }
}
