package com.alphadev.database.codecs;

import com.alphadev.enums.HouseRelationshipEnum;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class HouseRelationshipCodec implements Codec<HouseRelationshipEnum> {
    @Override
    public HouseRelationshipEnum decode(BsonReader reader, DecoderContext decoderContext) {
        if(reader.readString().equalsIgnoreCase("ally")){
            return HouseRelationshipEnum.ALLY;
        }
        else if(reader.readString().equalsIgnoreCase("neutral")){
            return HouseRelationshipEnum.NEUTRAL;
        }
        else return HouseRelationshipEnum.ENEMY;
    }

    @Override
    public void encode(BsonWriter writer, HouseRelationshipEnum value, EncoderContext encoderContext) {
        if(value!=null){
            writer.writeString(value.name());
        }
    }

    @Override
    public Class<HouseRelationshipEnum> getEncoderClass() {
        return HouseRelationshipEnum.class;
    }
}
