package com.alphadev.inventory;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.ItemFactoryUtil;
import com.alphadev.utils.LastInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HouseMenu implements Listener {

    public static final Inventory housesMenu = Bukkit.createInventory(null, 9, ChatColorUtil.boldText("Escolha sua casa", ChatColor.GREEN));

    static {
        housesMenu.setItem(0, buildZeroniaFrame());
        housesMenu.setItem(1, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(2, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(3, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(4, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(5, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(6, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(7, ItemFactoryUtil.menuDivisor());
        housesMenu.setItem(8, ItemFactoryUtil.menuDivisor());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if( housesMenu == null || event.getClickedInventory() == null)
            return;

        if(event.getInventory().equals(housesMenu))
            event.setCancelled(true);

       if(event.getCurrentItem().equals(new ItemStack(Material.PAPER))){

           House zeroniaHouse = new House( HouseOfChosenOne.getConfigFile(), "zeronia");
           event.getWhoClicked().sendMessage(zeroniaHouse.getObjective());
           // event.getWhoClicked().openInventory(housesMenu);
        }
    }




    private static ItemStack buildZeroniaFrame(){

    return ItemFactoryUtil.menuDivisor();
      //  return ItemFactoryUtil.house();
    }
}
