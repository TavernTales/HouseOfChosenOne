package com.alphadev.entity.codecs;

import com.alphadev.entity.Quest;
import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.UUID;

public class QuestCodec implements Codec<Quest> {
    @Override
    public Quest decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();

        UUID id = UUID.fromString(reader.readString("id"));
        String name = reader.readString("name");
        UUID owner = UUID.fromString(reader.readString("owner"));
        UUID completeBy = UUID.fromString(reader.readString("completeBy"));
        Integer contibuitionPoints = reader.readInt32("contibuitionPoints");
        QuestTypeEnum questTypeEnum = QuestTypeEnum.valueOf(reader.readString("questTypeEnum"));
        QuestTierEnum questTierEnum = QuestTierEnum.valueOf(reader.readString("questTierEnum"));
        Double vault = reader.readDouble("vault");
        Integer countRequired = reader.readInt32("countRequired");
        Long currentTime = reader.readInt64("currentTime");
        Boolean isComplete = reader.readBoolean("isComplete");

        reader.readEndDocument();

        return new Quest(id, name, owner, completeBy, contibuitionPoints, questTypeEnum, questTierEnum, vault, countRequired, currentTime, isComplete);
    }

    @Override
    public void encode(BsonWriter writer, Quest value, EncoderContext encoderContext) {
        writer.writeStartDocument();

        writer.writeString("id", value.getUUID().toString());
        writer.writeString("name", value.getName());
        writer.writeString("owner", value.getOwner().toString());
        writer.writeString("completeBy", value.getCompleteBy().toString());
        writer.writeInt32("contibuitionPoints", value.getContibuitionPoints());
        writer.writeString("questTypeEnum", value.getQuestTypeEnum().name());
        writer.writeString("questTierEnum", value.getQuestTierEnum().name());
        writer.writeDouble("vault", value.getVault());
        writer.writeInt32("countRequired", value.getCountRequired());
        writer.writeInt64("currentTime", value.getCurrentTime());

        writer.writeEndDocument();
    }

    @Override
    public Class<Quest> getEncoderClass() {
        return Quest.class;
    }
}
