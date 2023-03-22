package com.alphadev.utils;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class LastInventory {

    private static final HashMap<UUID, Inventory> lastInventoryPlayers = new HashMap<>();

    public void setLastInv(UUID playerUUID, Inventory lastInv) {
        lastInventoryPlayers.put(playerUUID,lastInv);
    }
    public  Inventory getLastInv(UUID playerUUID) {
        return lastInventoryPlayers.get(playerUUID);
    }
}
