package com.alphadev.enums;

import org.bukkit.ChatColor;

public enum QuestTierEnum {

    COMMON(1,"Comum", ChatColor.DARK_GREEN),
    UNCOMMON(2, "Incomum", ChatColor.BLUE),
    RARE(3, "Raro", ChatColor.LIGHT_PURPLE ),
    LEGENDARY(4, "Lend\u00E1rio", ChatColor.GOLD),
    CURSED(5, "Amaldi\u00E7oado",ChatColor.DARK_RED);
    private final Integer id;
    private final String name;
    private final ChatColor color;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public ChatColor getColor() {
        return color;
    }

    QuestTierEnum(Integer id, String name, ChatColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
