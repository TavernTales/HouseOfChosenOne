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
        UUID uuid = UUID.fromString(reader.readString("_id"));
        String playerName = reader.readString("playerName");
        Double currentXp = reader.readDouble("currentXp");
        Double maxXp = reader.readDouble("maxXp");
        Integer level = reader.readInt32("level");
        Double balance = reader.readDouble("balance");
        Integer core = reader.readInt32("core");
        Double health = reader.readDouble("health");
        Double maxHealth = reader.readDouble("maxHealth");
        Double mana = reader.readDouble("mana");
        Double maxMana = reader.readDouble("maxMana");
        reader.readEndDocument();
        return new PlayerData(uuid, playerName, currentXp, maxXp, level, balance, core, health, maxHealth, mana, maxMana);
    }

    @Override
    public void encode(BsonWriter writer, PlayerData playerData, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("_id", playerData.getUuid().toString());
        writer.writeString("playerName", playerData.getPlayerName());
        writer.writeDouble("currentXp", playerData.getCurrentXp());
        writer.writeDouble("maxXp", playerData.getMaxXp());
        writer.writeInt32("level", playerData.getLevel());
        writer.writeDouble("balance", playerData.getBalance());
        writer.writeInt32("core", playerData.getCore());
        writer.writeName("house");
        writer.writeDouble("health", playerData.getHealth());
        writer.writeDouble("maxHealth", playerData.getMaxHealth());
        writer.writeDouble("mana", playerData.getMana());
        writer.writeDouble("maxMana", playerData.getMaxMana());
        writer.writeEndDocument();
    }

    @Override
    public Class<PlayerData> getEncoderClass() {
        return PlayerData.class;
    }
}
