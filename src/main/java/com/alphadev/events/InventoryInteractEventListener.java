package com.alphadev.events;

import com.alphadev.services.QuestService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryInteractEventListener implements Listener {


    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event){
        new QuestService().onInventoryClickEvent(event);
    }



}
