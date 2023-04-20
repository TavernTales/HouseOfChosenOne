package com.alphadev.events;

import com.alphadev.entity.Quest;
import com.alphadev.events.quest.QuestProgress;
import com.alphadev.services.QuestService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import java.util.Arrays;

public class QuestEventHandler implements Listener {

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event){
        new QuestService().onInventoryClickEvent(event);
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
    @EventHandler()
    public void onPlayerHaverstEvent(BlockBreakEvent event){
        Player player = event.getPlayer();
        Arrays.stream(player.getInventory().getContents()).forEach(itemStack -> {
            if(itemStack == null || itemStack.getItemMeta() == null || itemStack.getItemMeta().getLore() == null)
                return;

            if(ChatColor.stripColor(itemStack.getItemMeta().getLore().get(2)).split("Objetivo: ")[1].equalsIgnoreCase(event.getBlock().getType().name())
                    && QuestService.questBookIsConcluded(itemStack)){

                QuestService.updateQuestionAmount(itemStack, player, 1);
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
            }
        });
    }
    @EventHandler
    public void onPlayerKillEntity(EntityDeathEvent event){

        if(event.getEntity().getKiller() == null)
            return;

        Player player = event.getEntity().getKiller();
        Arrays.stream(player.getInventory().getContents()).forEach(itemStack -> {
            if(itemStack == null || itemStack.getItemMeta() == null || itemStack.getItemMeta().getLore() == null)
                return;

            if(ChatColor.stripColor(itemStack.getItemMeta().getLore().get(2)).split("Objetivo: ")[1].equalsIgnoreCase(event.getEntity().getType().name())){
                QuestService.updateQuestionAmount(itemStack, player, 1);
            }

        });
    }
}
