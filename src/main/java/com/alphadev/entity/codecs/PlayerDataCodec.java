package com.alphadev.entity.codecs;

import com.alphadev.entity.PlayerData;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.UUID;

public class PlayerDataCodec implements Codec<PlayerData> {
    @Override
    public PlayerData decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        UUID uuid = UUID.fromString(reader.readString("uuid"));
        String playerName = reader.readString("playerName");
        reader.readEndDocument();
        return new PlayerData().setUUID(uuid).setPlayerName(playerName);
    }

    @Override
    public void encode(BsonWriter writer, PlayerData playerData, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("uuid", playerData.getUUID().toString());
        writer.writeString("playerName", playerData.getPlayerName());
        writer.writeEndDocument();
    }

    @Override
    public Class<PlayerData> getEncoderClass() {
        return PlayerData.class;
    }
}
