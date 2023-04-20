package com.alphadev.entity;
import java.util.UUID;
public class PlayerData {

    private UUID uuid;
    private String playerName;
    private Double currentXp;
    private Double maxXp;
    private Integer level;
    private Double balance;
    private Integer core;
    private Double health;
    private Double maxHealth;
    private Double mana;
    private Double maxMana;
    private Boolean isCandidate;
    private Boolean isChief;
    public PlayerData(UUID uuid, String playerName, Double currentXp, Double maxXp, Integer level, Double balance, Integer core, Double health, Double maxHealth, Double mana, Double maxMana) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.currentXp = currentXp;
        this.maxXp = maxXp;
        this.level = level;
        this.balance = balance;
        this.core = core;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        // Os campos Boolean não precisam entrar no construtor pois seriam dados a mais para cada jogador.
    }

    public PlayerData() {
        // Construtor vazio necessário para o Morphia
    }

    // getters e setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Double getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(Double currentXp) {
        this.currentXp = currentXp;
    }

    public Double getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(Double maxXp) {
        this.maxXp = maxXp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public Double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getMana() {
        return mana;
    }
    public void setMana(Double mana) {
        this.mana = mana;
    }
    public Double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(Double maxMana) {
        this.maxMana = maxMana;
    }

    // Getters e Setters para a "eleição de cada player".

    public Boolean getIsCandidate() {
        return isCandidate;
    }

    public void setIsCandidate(Boolean isCandidate) {
        this.isCandidate = isCandidate;
    }

    public Boolean getIsChief() {
        return isChief;
    }

    public void setIsChief(Boolean isChief) {
        this.isChief = isChief;
    }
}