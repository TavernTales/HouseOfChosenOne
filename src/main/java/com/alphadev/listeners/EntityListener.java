package com.alphadev.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void entityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) {
            return;
        }

//        Player p = e.getEntity().getKiller();
//        if (QuestManager.quests.containsKey(p.getUniqueId())) {
//            QuestProgress questProgress = new QuestProgress(QuestManager.quests.get(p.getUniqueId()), p, e.getEntityType());
//            Bukkit.getServer().getPluginManager().callEvent(questProgress);
//        }
    }

}
