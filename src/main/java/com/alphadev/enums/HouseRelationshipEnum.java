package com.alphadev.enums;

import org.bukkit.Material;

public enum HouseRelationshipEnum {
    ALLY(Material.KELP),
    NEUTRAL(Material.SNOWBALL),
    ENEMY(Material.ROTTEN_FLESH);

    private final Material icon;
    HouseRelationshipEnum(Material material) {
        this.icon = material;
    }

    public Material getIcon() {
        return icon;
    }
}
