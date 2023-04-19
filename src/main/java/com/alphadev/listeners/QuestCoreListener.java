package com.alphadev.listeners;

import com.alphadev.entity.Quest;
import com.alphadev.events.quest.QuestProgress;
import com.alphadev.events.quest.QuestStartEvent;
import com.alphadev.services.QuestManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class QuestCoreListener implements Listener {
    @EventHandler
    public void onQuestStart(QuestStartEvent e){
        Player player = e.getPlayer();
        ItemStack book = e.getBookQuest();
        // Cria e adiciona o livro no inventário do player
        player.getInventory().addItem(book);
    }
    @EventHandler
    public void onQuestProgress(QuestProgress e) {
        Player player = e.getPlayer();
        Quest quest = e.getQuest();
        if (QuestManager.hasQuestBook(player, quest)) {
            // O jogador possui o livro da quest
            switch (quest.getQuestTypeEnum()){
                case DEFEAT, HUNTER ,PVP -> {
                    if(quest.getMobObjective().equals(e.getEntityObjective())){
                        QuestManager.questDecrementCount(quest);
                        player.playSound(player, Sound.BLOCK_REDSTONE_TORCH_BURNOUT,1F,1F);
                    }

                }
                case DELIVERY, HARVEST -> {
                    // TODO
                }
            }
        }
    }
}

