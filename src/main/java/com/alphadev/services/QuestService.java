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
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class QuestService {

    public void questManagerPainel(Player player){
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


    public void createQuest(Player player){
        Inventory inventory = Bukkit.createInventory( player, InventoryType.ANVIL, ChatColorUtil.boldText("Nome da Miss\u00E3o", ChatColor.RED));
        inventory.addItem(ItemFactoryUtil.questNameItem());
        player.openInventory(inventory);
    }

    public void createNameQuest(Player player, String questName){
        player.sendMessage(questName);
    }

    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null)
         return;

        if(event.getCurrentItem().equals(ItemFactoryUtil.questCreate())){
            event.setCancelled(true);
            createQuest((Player) event.getWhoClicked());
        }

        if(event.getCurrentItem().equals(ItemFactoryUtil.questNameItem())){
            event.setCancelled(true);
        }

    }

}
