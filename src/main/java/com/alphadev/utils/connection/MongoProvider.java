package com.alphadev.utils.connection;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.PlayerData;
import com.alphadev.entity.Quest;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.configuration.ConfigurationSection;

public class MongoProvider {
    private static MongoProvider instance;
    private final MongoDatabase database;
    private static final String MONGODB_URI;
    private static final String MONGODB_NAME;
    private final CodecRegistry codecRegistry;

    static {
        ConfigurationSection settingsFile = HouseOfChosenOne.getConfigFile().getConfigurationSection("settings");
        MONGODB_URI = settingsFile != null && settingsFile.contains("mongodb-uri") ? settingsFile.getString("mongodb-uri") : "mongodb://localhost/hoco";
        MONGODB_NAME = settingsFile != null && settingsFile.contains("mongodb-name") ? settingsFile.getString("mongodb-name") : "houseOfChosenOne";
    }

    private MongoProvider() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register(PlayerData.class, Quest.class).build();
        codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(MONGODB_URI))
                .codecRegistry(codecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(MONGODB_NAME);
    }

    public static synchronized MongoProvider getInstance() {
        if (instance == null) {
            instance = new MongoProvider();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
