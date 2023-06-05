package com.alphadev.listeners;

import org.bukkit.event.Listener;

public class QuestListener implements Listener {
    /*
    @EventHandler
    public void onQuestStart(QuestStartEvent e) {
        Player player = e.getPlayer();
        ItemStack book = e.getBookQuest();
        // Cria e adiciona o livro no inventário do player
        player.getInventory().addItem(book);
    }

    @EventHandler
    public void onQuestProgress(QuestProgress e) {
        Player player = e.getPlayer();
        HcoQuest hcoQuest = e.getQuest();
        if (QuestManager.hasQuestBook(player, hcoQuest)) {
            // O jogador possui o livro da quest
            switch (hcoQuest.getQuestTypeEnum()) {
                case DEFEAT, HUNTER, PVP -> {
                    if (hcoQuest.getMobObjective().equals(e.getEntityObjective())) {
                        QuestManager.questDecrementCount(hcoQuest);
                        player.playSound(player, Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
                    }

                }
                case DELIVERY, HARVEST -> {
                    // TODO
                }
            }
        }
    }*/
}

