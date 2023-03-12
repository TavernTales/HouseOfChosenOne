package com.alphadev.utils;

import org.bukkit.inventory.Inventory;

public class LastInventory {
    public static Inventory lastInv = null;
    public static void setLastInv(Inventory lastInv) {
        LastInventory.lastInv = lastInv;
    }
    public static Inventory getLastInv() {
        return lastInv;
    }
}
