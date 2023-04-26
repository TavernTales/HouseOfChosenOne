package com.alphadev.entity;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import dev.morphia.annotations.Reference;

import java.util.List;
import java.util.UUID;
@Entity("PlayerData")
public class PlayerData {
    @Id
    private UUID uuid;
    @Indexed
    private String playerName;
    @Reference
    private House house;

    public Long getResetTimeout() {
        return resetTimeout;
    }

    public PlayerData setResetTimeout(Long resetTimeout) {
        this.resetTimeout = resetTimeout;
        return this;
    }

    private Long resetTimeout;
    private Boolean isCandidate;
    private Boolean isChief;
    private List<String> permissions;
    private Integer contributions;

    public UUID getUUID() {
        return uuid;
    }

    public PlayerData setUUID(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerData setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public PlayerData setHouse(House house) {
        this.house = house;
        return this;
    }

    public Boolean getCandidate() {
        return isCandidate;
    }

    public PlayerData setCandidate(Boolean candidate) {
        isCandidate = candidate;
        return this;
    }

    public Boolean getChief() {
        return isChief;
    }

    public PlayerData setChief(Boolean chief) {
        isChief = chief;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public PlayerData setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Integer getContributions() {
        return contributions;
    }

    public PlayerData setContributions(Integer contributions) {
        this.contributions = contributions;
        return this;
    }
}