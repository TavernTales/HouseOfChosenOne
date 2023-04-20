package com.alphadev.enums;

public enum QuestTypeEnum {
    DEFEAT(1, "Eliminacao"),
    DELIVERY(4, "Entrega"),
    HARVEST(5, "Colheita"),
    HUNTER(3, "Cacada"),
    PVP(2, "Combate");
    private final byte id;
    private final String name;

    QuestTypeEnum(int id, String name) {
        this.id = (byte) id;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}