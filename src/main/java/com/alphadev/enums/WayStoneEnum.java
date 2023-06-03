package com.alphadev.enums;

import java.util.Arrays;

public enum WayStoneEnum {

    NETHERLAND(1, "\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u2295", "Netherland"),
    BROTHERLAND(4, "\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u2296", "Brotherland"),
    JAIL(5, "\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u00A6\u2297", "Jail"),
    UNKNOW(0,"", "");

    private final byte id;
    private final String title;
    private final String name;

    WayStoneEnum(int id, String title, String name) {
        this.id = (byte) id;
        this.title = title;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public static WayStoneEnum fromTitle(String text) {
        return  Arrays.stream(WayStoneEnum.values()).filter(wayStoneEnum -> wayStoneEnum.getTitle().equalsIgnoreCase(text)).findFirst().orElse(WayStoneEnum.UNKNOW);
    }
}
