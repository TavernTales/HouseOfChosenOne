package com.alphadev.entity.codecs;

import com.alphadev.entity.HouseLocation;
import org.bson.*;
import org.bson.codecs.*;


public class LocationCodec implements Codec<HouseLocation> {

    @Override
    public void encode(BsonWriter writer, HouseLocation value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeNull("world");
        writer.writeDouble("x",value.getX());
        writer.writeDouble("y",value.getY());
        writer.writeDouble("z",value.getZ());
        writer.writeDouble("pitch",value.getPitch());
        writer.writeDouble("yaw",value.getPitch());
        writer.writeEndDocument();
    }

    @Override
    public HouseLocation decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readNull("world");
        double x = reader.readDouble("x");
        double y = reader.readDouble("y");
        double z = reader.readDouble("z");
        float pitch = (float) reader.readDouble("pitch");
        float yaw = (float) reader.readDouble("yaw");
        return new HouseLocation(x,y,z,yaw,pitch);
    }

    @Override
    public Class<HouseLocation> getEncoderClass() {
        return HouseLocation.class;
    }
}