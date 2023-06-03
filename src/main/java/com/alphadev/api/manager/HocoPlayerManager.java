package com.alphadev.api.manager;

import com.alphadev.api.entities.HocoHouse;
import com.alphadev.api.entities.HocoPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public interface HocoPlayerManager {
    HocoPlayer getHocoPlayerFromUUID(UUID uuid);
    boolean playerIsExiled(UUID uuid);
    boolean playerIsExiled(Player player);
    Collection<OfflinePlayer> getAllPlayer();
    ArrayList<? extends HocoPlayer> getAllPlayersFromDB();
    ArrayList<? extends HocoPlayer> getAllPlayersHouse(HocoHouse house);
    ArrayList<? extends HocoPlayer> getAllPlayersHouse(String name);
    ArrayList<? extends HocoPlayer> getAllPlayersHouse(Long id);

}
