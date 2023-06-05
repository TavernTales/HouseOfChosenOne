package com.alphadev.api.manager;

import com.alphadev.api.entities.IHOCOHouse;
import com.alphadev.api.entities.IHOCOPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public interface IHOCOPlayerManager {
    IHOCOPlayer getHocoPlayerFromUUID(UUID uuid);
    boolean playerIsExiled(UUID uuid);
    boolean playerIsExiled(Player player);
    Collection<OfflinePlayer> getAllPlayer();
    ArrayList<? extends IHOCOPlayer> getAllPlayersFromDB();
    ArrayList<? extends IHOCOPlayer> getAllPlayersHouse(IHOCOHouse house);
    ArrayList<? extends IHOCOPlayer> getAllPlayersHouse(String name);
    ArrayList<? extends IHOCOPlayer> getAllPlayersHouse(Long id);

}
