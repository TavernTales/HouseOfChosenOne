package com.alphadev.api;

import com.alphadev.api.manager.HocoHouseManager;
import com.alphadev.api.manager.HocoPlayerManager;
import org.bukkit.Location;

import java.util.Map;
import java.util.Objects;

public interface HocoAPI {
    HocoPlayerManager getPlayerManager();
    HocoHouseManager getHouseManager();
    Location mapToLocation(Map<String, Objects> map);
}
