package com.alphadev.enums;

public enum QuestTierEnum {

    COMMON(1L),
    UNCOMMON(2L),
    RARE(3L),
    LEGENDARY(4L),
    CURSED(5L);
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    QuestTierEnum(Long id) {
        this.id = id;
    }

}
