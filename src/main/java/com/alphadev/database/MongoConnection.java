package com.alphadev.database;

import com.alphadev.database.codecs.InternalPropertyCodecProvider;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.plugin.Plugin;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {
    private static MongoConnection instance;
    private final MongoClient mongoClient;

    private MongoConnection(Plugin plugin) {
        // Recupera as configurações do plugin
        String uri = plugin.getConfig().getString("mongodb.server.uri");
        String database = plugin.getConfig().getString("mongodb.server.database");
        assert uri != null;
        assert database != null;

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                        .register(new InternalPropertyCodecProvider())
                        .automatic(true)
                        .build()));

        // Configura o cliente do MongoDB
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(codecRegistry)
                .build();

        // Inicia o cliente MongoDB.
        mongoClient = MongoClients.create(settings);
    }
    // Cria ou recupera a instancia da classe singleton
    public static MongoConnection getInstance(Plugin plugin) {
        if (instance == null) {
            instance = new MongoConnection(plugin);
        }
        return instance;
    }
    public MongoClient getMongoClient() {
        return mongoClient;
    }
    public void close(){
        mongoClient.close();
    }
}
