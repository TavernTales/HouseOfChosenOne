package com.alphadev.listeners;

import com.alphadev.events.quest.QuestProgress;
import com.alphadev.services.QuestManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityTypeQuestListener implements Listener {

    @EventHandler
    public void entityDeath(EntityDeathEvent e){
        if (!(e.getEntity().getKiller() instanceof Player)) return;
        Player p = e.getEntity().getKiller();
        if(QuestManager.quests.containsKey(p.getUniqueId())) {
            QuestProgress questProgress = new QuestProgress(QuestManager.quests.get(p.getUniqueId()),p,e.getEntityType());
            Bukkit.getServer().getPluginManager().callEvent(questProgress);
        }
    }

}
