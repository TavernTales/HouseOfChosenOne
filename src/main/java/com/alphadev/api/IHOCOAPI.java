package com.alphadev.api;

import com.alphadev.api.manager.IHouseManager;
import com.alphadev.api.manager.IHOCOPlayerManager;
import org.bukkit.Location;

import java.util.Map;
import java.util.Objects;

public interface IHOCOAPI {
    IHOCOPlayerManager getPlayerManager();
    IHouseManager getHouseManager();
    Location mapToLocation(Map<String, Objects> map);
}
