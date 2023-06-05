package com.alphadev;

import com.alphadev.api.IHOCOAPI;

import com.alphadev.database.MongoConnection;
import com.alphadev.manager.HouseManager;
import com.alphadev.manager.PlayerManager;
import com.mongodb.client.MongoDatabase;
import org.bukkit.Location;

import java.util.Map;
import java.util.Objects;

public class HOCOProvider implements IHOCOAPI {

    private static final HOCOProvider instance = getInstance();
    private final PlayerManager playerManager = new PlayerManager();
    private final HouseManager houseManager = new HouseManager();
    private static final MongoConnection connection = MongoConnection.getInstance(HOCOPlugin.getPlugin());


    public static MongoDatabase getMongoDatabase() {
        return connection.getMongoClient().getDatabase("hoco");
    }
    public static HOCOProvider getInstance() {

        if(instance == null)
            return new HOCOProvider();

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
        return new Location(HOCOPlugin.getPlugin().getServer().getWorld(worldName), x,y,z);
    }

    public MongoConnection getConnection() {
        return connection;
    }
}
