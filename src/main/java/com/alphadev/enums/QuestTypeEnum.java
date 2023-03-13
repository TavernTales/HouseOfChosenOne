package com.alphadev.enums;

public enum QuestTypeEnum {
    DELIVERY(1L),
    DEFEAT(2L),
    HUNTER(3L);
    private Long id;

    QuestTypeEnum(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
