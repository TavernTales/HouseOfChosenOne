package com.alphadev.utils.connection;

import com.alphadev.HouseOfChosenOne;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bukkit.configuration.ConfigurationSection;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;


public class MongoProvider {
    private static MongoProvider instance;
    private final Datastore datastore;
    private static final ConfigurationSection settingsFile = HouseOfChosenOne.getConfigFile().getConfigurationSection("settings");
    private static final String MONGODB_URI;
    private static final String MONGODB_NAME;

    static  {
        MONGODB_URI = settingsFile != null  && settingsFile.contains("mongodb-uri") ? settingsFile.getString("mongodb-uri") : "mongodb://localhost:27017";
        MONGODB_NAME = settingsFile != null  && settingsFile.contains("mongodb-name") ? settingsFile.getString("mongodb-name") : "houseOfChosenOne";
    }

    private MongoProvider() {
        MongoClient client = new MongoClient(new MongoClientURI(MONGODB_URI));
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.alphadev.entity");
        datastore = morphia.createDatastore(client, MONGODB_NAME);
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
