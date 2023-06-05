package com.alphadev.api.entities;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface IHOCOPlayer {
    Player getPlayerBukkit();

    OfflinePlayer getOfflinePlayerBukkit();
}
