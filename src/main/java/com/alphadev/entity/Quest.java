package com.alphadev.entity;

import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class Quest {
    private String name;
    private Integer contibuitionPoints;
    private List<ItemStack> itensRequired;
    private String itemRequired;
    private List<EntityType> mobsRequired;
    private String mobRequired;

    private List<Material> haverstItensRequired;
    private String haverstItemRequired;

    private QuestTypeEnum questTypeEnum;
    private QuestTierEnum questTierEnum;

    private Double vault;
    private Long countRequired;
    private List<Player> playersInProgress;
    private House house;
    private Long currentTime;

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

    public List<ItemStack> getItensRequired() {
        return itensRequired;
    }

    public void setItensRequired(List<ItemStack> itensRequired) {
        this.itensRequired = itensRequired;
    }

    public String getItemRequired() {
        return itemRequired;
    }

    public void setItemRequired(String itemRequired) {
        this.itemRequired = itemRequired;
    }

    public List<EntityType> getMobsRequired() {
        return mobsRequired;
    }

    public void setMobsRequired(List<EntityType> mobsRequired) {
        this.mobsRequired = mobsRequired;
    }

    public String getMobRequired() {
        return mobRequired;
    }

    public void setMobRequired(String mobRequired) {
        this.mobRequired = mobRequired;
    }

    public List<Material> getHaverstItensRequired() {
        return haverstItensRequired;
    }

    public void setHaverstItensRequired(List<Material> haverstItensRequired) {
        this.haverstItensRequired = haverstItensRequired;
    }

    public String getHaverstItemRequired() {
        return haverstItemRequired;
    }

    public void setHaverstItemRequired(String haverstItemRequired) {
        this.haverstItemRequired = haverstItemRequired;
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

    public Long getCountRequired() {
        return countRequired;
    }

    public void setCountRequired(Long countRequired) {
        this.countRequired = countRequired;
    }

    public List<Player> getPlayersInProgress() {
        return playersInProgress;
    }

    public void setPlayersInProgress(List<Player> playersInProgress) {
        this.playersInProgress = playersInProgress;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
