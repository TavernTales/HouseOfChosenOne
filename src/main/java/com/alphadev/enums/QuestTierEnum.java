package com.alphadev.enums;

import org.bukkit.ChatColor;

public enum QuestTierEnum {

    COMMON(1L,"Comum", ChatColor.DARK_GREEN),
    UNCOMMON(2L, "Incomum", ChatColor.BLUE),
    RARE(3L, "Raro", ChatColor.LIGHT_PURPLE ),
    LEGENDARY(4L, "Lend\u00E1rio", ChatColor.GOLD),
    CURSED(5L, "Amaldi\u00E7oado",ChatColor.DARK_RED);
    private Long id;
    private String name;
    private ChatColor color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    QuestTierEnum(Long id, String name, ChatColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
