package com.alphadev.events.quest;

import com.alphadev.entity.Quest;
import com.alphadev.enums.QuestTypeEnum;
import com.alphadev.events.QuestsOnProgressEvent;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class QuestProgress extends QuestsOnProgressEvent {
    private static final HandlerList handlers = new HandlerList();
    private final QuestTypeEnum questType;
    private final Player player;
    private EntityType entityObjective;
    private Material itemObjective;
    public QuestProgress(Quest quest, Player player, EntityType entityType, Material itemStack) {
        super(quest);
        this.player = player;
        this.questType = quest.getQuestTypeEnum();
        this.entityObjective = entityType;
        this.itemObjective = itemStack;
    }

    public QuestProgress(Quest quest, Player player, EntityType entityObjective) {
        super(quest);
        this.player = player;
        this.entityObjective = entityObjective;
        this.questType = quest.getQuestTypeEnum();
    }

    public QuestProgress(Quest quest,Player player, Material itemObjective) {
        super(quest);
        this.player = player;
        this.itemObjective = itemObjective;
        this.questType = quest.getQuestTypeEnum();
    }

    public int getQuestTypeId() {
        return questType.getId();
    }
    public Player getPlayer() {
        player.getInventory();
        return player;

    }

    public EntityType getEntityObjective() {
        return entityObjective;
    }

    public void setEntityObjective(EntityType entityObjective) {
        this.entityObjective = entityObjective;
    }

    public Material getItemObjective() {
        return itemObjective;
    }

    public void setItemObjective(Material itemObjective) {
        this.itemObjective = itemObjective;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
