package com.alphadev.entities;

import com.alphadev.api.annotation.HocoEntity;
import com.alphadev.api.entities.HocoHouse;
import com.alphadev.enums.HouseRelationshipEnum;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonExtraElements;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import java.util.Map;

@HocoEntity("hco_houses")
public record HcoHouse(@BsonId Long id, String name, String description, String objective, @BsonRepresentation(BsonType.DOCUMENT) HcoPlayer leader, Integer contribution, Map<Long, HouseRelationshipEnum> relationshipMap, @BsonExtraElements Map<String,Object> location) implements HocoHouse {
    public HcoHouse withLeader(HcoPlayer leader){
        return new HcoHouse(id(),name(),description(),objective(),leader,contribution(),relationshipMap(),location());
    }
}
