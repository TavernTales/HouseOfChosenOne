package com.alphadev.enums;

import org.bukkit.ChatColor;

import java.util.Arrays;

public enum HouseEnum {

    ZERONIA(1, "zeronia", ChatColor.RED),
    VLAROLA(2, "vlarola", ChatColor.BLUE),
    FRANDHRA(3, "frandhra", ChatColor.DARK_GREEN),
    NASHOR(4, "nashor", ChatColor.DARK_RED),
    DRAKKARIS(5, "drakkaris", ChatColor.DARK_GRAY);
    private final Integer id;
    private final String name;
    private final ChatColor color;

    HouseEnum(Integer id, String name, ChatColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static HouseEnum fromName(String text) {
        return Arrays.stream(HouseEnum.values()).filter(houseEnum -> houseEnum.getName().equalsIgnoreCase(text)).findFirst().orElse(HouseEnum.ZERONIA);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
