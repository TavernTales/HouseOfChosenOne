package com.alphadev.database.repositories;


import com.alphadev.api.repository.HocoRepository;
import com.alphadev.entities.HcoPlayer;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import java.util.UUID;


public class HcoPlayerRepository extends HocoRepository<UUID,HcoPlayer> {
    public HcoPlayerRepository() {
        super();
    }

    public void updatePlayerContribution(UUID uuid, int quantity){
        Bson filter = Filters.eq("_id", uuid);
        Bson update = Updates.inc("contribution",quantity);
        collection.findOneAndUpdate(filter,update);
    }
}
