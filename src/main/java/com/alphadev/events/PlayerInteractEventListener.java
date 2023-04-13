package com.alphadev.events;

import com.alphadev.services.QuestService;
import com.alphadev.services.SignHouseService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public class PlayerInteractEventListener implements Listener {

    @EventHandler
    public void signHouseInteration(PlayerInteractEvent event){
        SignHouseService.signHouseInteract(event);
    }


    @EventHandler
    public void playerPickUpEvent(InventoryPickupItemEvent event){
        if(!(event.getInventory().getHolder() instanceof Player player))
            return;

        Arrays.stream(event.getInventory().getContents()).forEach(itemStack -> {
            if(itemStack == null || itemStack.getItemMeta() == null || itemStack.getItemMeta().getLore() == null)
                return;

            if(ChatColor.stripColor(itemStack.getItemMeta().getLore().get(2)).split("Objetivo: ")[1].equalsIgnoreCase(event.getItem().getItemStack().getType().name())){
                QuestService.updateQuestionAmount(itemStack, player, 1);
            }
        });
    }
}
