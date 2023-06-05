package com.alphadev.entities;

import com.alphadev.api.annotation.HOCOEntity;
import com.alphadev.api.entities.IHOCOHouse;
import com.alphadev.enums.HouseRelationshipEnum;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonExtraElements;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import java.util.Map;

@HOCOEntity("hco_houses")
public record HCOHouse(@BsonId Long id, String name, String description, String objective, @BsonRepresentation(BsonType.DOCUMENT) HCOPlayer leader, Integer contribution, Map<Long, HouseRelationshipEnum> relationshipMap, @BsonExtraElements Map<String,Object> location) implements IHOCOHouse {
    public HCOHouse withLeader(HCOPlayer leader){
        return new HCOHouse(id(),name(),description(),objective(),leader,contribution(),relationshipMap(),location());
    }
}
