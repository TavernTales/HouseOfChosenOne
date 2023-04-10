package com.alphadev.events;

import com.alphadev.services.QuestService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;

public class PlayerBreakEventListener implements Listener {


    @EventHandler()
    public void onPlayerHaverstEvent(BlockBreakEvent event){
        Player player = event.getPlayer();
        Arrays.stream(player.getInventory().getContents()).forEach(itemStack -> {
            if(itemStack == null || itemStack.getItemMeta() == null || itemStack.getItemMeta().getLore() == null)
                return;

            if(ChatColor.stripColor(itemStack.getItemMeta().getLore().get(2)).split("Objetivo: ")[1].equalsIgnoreCase(event.getBlock().getType().name())
                && !QuestService.questBookIsConcluded(itemStack)){

                QuestService.updateQuestionAmount(itemStack, player, 1);
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
            }
        });
    }
}
