package com.alphadev;

import com.alphadev.api.HocoAPI;

import com.alphadev.database.MongoConnection;
import com.alphadev.manager.HouseManager;
import com.alphadev.manager.PlayerManager;
import com.mongodb.client.MongoDatabase;
import org.bukkit.Location;

import java.util.Map;
import java.util.Objects;

public class Hoco implements HocoAPI {
    private static MongoDatabase mongoDatabase;
    private static HocoPlugin hocoPlugin;
    private static Hoco instance;
    private final PlayerManager playerManager;
    private final HouseManager houseManager;
    private static MongoConnection connection;

    public Hoco(HocoPlugin plugin) {
        hocoPlugin = plugin;
        instance = this;
        connection = MongoConnection.getInstance(plugin);
        mongoDatabase = getMongoDatabase();
        playerManager = new PlayerManager(this);
        houseManager = new HouseManager(this);
    }

    public static MongoDatabase getMongoDatabase() {
        mongoDatabase = connection.getMongoClient().getDatabase("hoco");
        return mongoDatabase;
    }
    public static Hoco getInstance() {
        return instance;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    @Override
    public HouseManager getHouseManager() {
        return houseManager;
    }

    @Override
    public Location mapToLocation(Map<String, Objects> map) {
        String worldName = map.get("world").toString();
        double x = Double.parseDouble(map.get("x").toString());
        double y = Double.parseDouble(map.get("y").toString());
        double z = Double.parseDouble(map.get("z").toString());
        return new Location(hocoPlugin.getServer().getWorld(worldName), x,y,z);
    }

    public MongoConnection getConnection() {
        return connection;
    }
}
