package com.alphadev.utils.connection;


import com.alphadev.HouseOfChosenOne;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bukkit.configuration.ConfigurationSection;


public class MongoProvider {
    private static MongoProvider instance;
    private final Datastore datastore;
    private static final ConfigurationSection settingsFile = HouseOfChosenOne.getConfigFile().getConfigurationSection("settings");
    private static final String MONGODB_URI;
    private static final String MONGODB_NAME;

    static  {
        MONGODB_URI = settingsFile != null  && settingsFile.contains("mongodb-uri") ? settingsFile.getString("mongodb-uri") : "mongodb+srv://hoc_db:123456!@hoc.oxosawo.mongodb.net/test";
        MONGODB_NAME = settingsFile != null  && settingsFile.contains("mongodb-name") ? settingsFile.getString("mongodb-name") : "houseOfChosenOne";
    }

    private MongoProvider() {

        datastore = Morphia.createDatastore(MongoClients.create(MONGODB_URI), MONGODB_NAME);
        datastore.getMapper().mapPackage("com.alphadev.entity");

        datastore.ensureIndexes();
    }

    public static MongoProvider getInstance() {
        if (instance == null) {
            instance = new MongoProvider();
        }
        return instance;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
