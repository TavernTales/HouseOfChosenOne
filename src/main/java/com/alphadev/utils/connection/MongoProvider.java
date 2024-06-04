package com.alphadev.utils.connection;


import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.entity.HouseLocation;
import com.alphadev.entity.PlayerData;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bson.UuidRepresentation;
import org.bukkit.configuration.ConfigurationSection;


public class MongoProvider {
    private static MongoProvider instance;
    private final Datastore datastore;
    private static final ConfigurationSection settingsFile = HouseOfChosenOne.getConfigFile().getConfigurationSection("settings");
    private static final String MONGODB_URI;
    private static final String MONGODB_NAME;

    static  {
        MONGODB_URI = settingsFile != null  && settingsFile.contains("mongodb-uri") ? settingsFile.getString("mongodb-uri") : "mongo_uri";
        MONGODB_NAME = settingsFile != null  && settingsFile.contains("mongodb-name") ? settingsFile.getString("mongodb-name") : "houseOfChosenOne";
    }

    private MongoProvider() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(MONGODB_URI))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        datastore = Morphia.createDatastore(MongoClients.create(settings), MONGODB_NAME);
        datastore.getMapper().map(HouseLocation.class,PlayerData.class, House.class);

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
