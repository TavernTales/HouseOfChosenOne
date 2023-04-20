package com.alphadev.entity;

import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public class Quest {
    private UUID uuid;
    private String name;
    private UUID owner;
    private UUID completeBy;
    private Integer contibuitionPoints;
    private QuestTypeEnum questTypeEnum;
    private EntityType mobObjective;
    private ItemStack itemObjective;
    private QuestTierEnum questTierEnum;
    private Double vault;
    private Integer countRequired;
    private Long currentTime;
    private Boolean isComplete;

    public Quest() {
        // Construtor vazio necessário para o Morphia
    }

    public Quest(UUID uuid, String name, UUID owner, UUID completeBy, Integer contibuitionPoints, QuestTypeEnum questTypeEnum, QuestTierEnum questTierEnum, Double vault, Integer countRequired, Long currentTime, Boolean isComplete) {
        this.uuid = uuid;
        this.name = name;
        this.owner = owner;
        this.completeBy = completeBy;
        this.contibuitionPoints = contibuitionPoints;
        this.questTypeEnum = questTypeEnum;
        this.questTierEnum = questTierEnum;
        this.vault = vault;
        this.countRequired = countRequired;
        this.currentTime = currentTime;
        this.isComplete = isComplete;
    }

    // getters e setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContibuitionPoints() {
        return contibuitionPoints;
    }

    public void setContibuitionPoints(Integer contibuitionPoints) {
        this.contibuitionPoints = contibuitionPoints;
    }

    public QuestTypeEnum getQuestTypeEnum() {
        return questTypeEnum;
    }

    public void setQuestTypeEnum(QuestTypeEnum questTypeEnum) {
        this.questTypeEnum = questTypeEnum;
    }

    public QuestTierEnum getQuestTierEnum() {
        return questTierEnum;
    }

    public void setQuestTierEnum(QuestTierEnum questTierEnum) {
        this.questTierEnum = questTierEnum;
    }

    public Double getVault() {
        return vault;
    }

    public void setVault(Double vault) {
        this.vault = vault;
    }

    public Integer getCountRequired() {
        return countRequired;
    }

    public void setCountRequired(Integer countRequired) {
        this.countRequired = countRequired;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(UUID completeBy) {
        this.completeBy = completeBy;
    }

    public EntityType getMobObjective() {
        return mobObjective;
    }

    public void setMobObjective(EntityType mobObjective) {
        this.mobObjective = mobObjective;
    }

    public ItemStack getItemObjective() {
        return itemObjective;
    }

    public void setItemObjective(ItemStack itemObjective) {
        this.itemObjective = itemObjective;
    }
}
