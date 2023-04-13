package com.alphadev.events;

import com.alphadev.services.QuestService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Arrays;

public class PlayerKillEntityEventListener implements Listener {

    @EventHandler
    public void onPlayerKillEntity(EntityDeathEvent event){

        if(!(event.getEntity().getKiller() instanceof Player))
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
