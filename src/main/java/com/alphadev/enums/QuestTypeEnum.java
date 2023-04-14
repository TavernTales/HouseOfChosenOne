package com.alphadev.enums;

public enum QuestTypeEnum {
    DELIVERY(1L, "Entrega"),
    DEFEAT(2L, "Elimine"),
    HUNTER(3L, "Ca\u00E7a"),
    HARVEST(4L, "Coleta"),
    PVP(5L, "Combate");
    private final Long id;
    private final String name;

    QuestTypeEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

}
