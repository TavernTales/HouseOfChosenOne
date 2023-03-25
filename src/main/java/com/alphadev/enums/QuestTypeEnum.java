package com.alphadev.enums;

public enum QuestTypeEnum {
    DELIVERY(1L, "Entrega de Itens"),
    DEFEAT(2L, "Elimine Criaturas"),
    HUNTER(3L, "Ca\u00E7a de Animais"),
    HARVEST(4L, "Colheita"),
    PVP(5L, "Combate");
    private Long id;
    private String name;

    QuestTypeEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }
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
}
