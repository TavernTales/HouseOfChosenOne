package com.alphadev.enums;

import org.bukkit.ChatColor;

public enum QuestTierEnum {

    COMMON(1L,"Comum", ChatColor.DARK_GREEN),
    UNCOMMON(2L, "Incomum", ChatColor.BLUE),
    RARE(3L, "Raro", ChatColor.LIGHT_PURPLE ),
    LEGENDARY(4L, "Lend\u00E1rio", ChatColor.GOLD),
    CURSED(5L, "Amaldi\u00E7oado",ChatColor.DARK_RED);
    private final Long id;
    private final String name;
    private final ChatColor color;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public ChatColor getColor() {
        return color;
    }

    QuestTierEnum(Long id, String name, ChatColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
