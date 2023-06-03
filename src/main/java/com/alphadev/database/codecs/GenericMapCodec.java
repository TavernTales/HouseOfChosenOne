package com.alphadev.database.codecs;

import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.json.JsonReader;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenericMapCodec<K,V> implements Codec<Map<K,V>> {
    Class<Map<K, V>> encoderClass;
    Codec<K> keyCodec;
    Codec<V> valueCodec;
    public GenericMapCodec(Class<Map<K, V>> encoderClass, Codec<K> keyCodec, Codec<V> valueCodec) {
        this.encoderClass = encoderClass;
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Map<K, V> decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        Map<K, V> map = getInstance();
        while (!BsonType.END_OF_DOCUMENT.equals(reader.readBsonType())) {
            K key;
            PropertyEditor editor = PropertyEditorManager.findEditor(keyCodec.getEncoderClass());
            if (editor != null) {
                editor.setAsText(reader.readName());
                key = (K) editor.getValue();
            } else {
                JsonReader jsonReader = new JsonReader(String.format("\"key\": \"%s\"", reader.readName()));
                key = keyCodec.decode(jsonReader, decoderContext);
            }

            V value = null;
            if (!BsonType.NULL.equals(reader.getCurrentBsonType())) {
                value = valueCodec.decode(reader, decoderContext);
            }
            map.put(key, value);
        }
        reader.readEndDocument();
        return map;
    }

    @Override
    public void encode(BsonWriter writer, Map<K, V> value, EncoderContext encoderContext) {
        try (BsonDocumentWriter documentWriter = new BsonDocumentWriter(new BsonDocument())) {
            documentWriter.writeStartDocument();
            writer.writeStartDocument();

            for (Map.Entry<K, V> entry : value.entrySet()) {
                PropertyEditor editor = PropertyEditorManager.findEditor(keyCodec.getEncoderClass());
                if (editor != null) {
                    editor.setValue(entry.getKey());
                    writer.writeName(editor.getAsText());
                } else {
                    String documentId = UUID.randomUUID().toString();
                    documentWriter.writeName(documentId);
                    keyCodec.encode(documentWriter, entry.getKey(), encoderContext);
                    writer.writeName(documentWriter.getDocument().asDocument().get(documentId).asString().getValue());
                }

                valueCodec.encode(writer, entry.getValue(), encoderContext);
            }
            documentWriter.writeEndDocument();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        writer.writeEndDocument();
    }

    @Override
    public Class<Map<K, V>> getEncoderClass() {
        return encoderClass;
    }
    private Map<K, V> getInstance() {
        if (encoderClass.isInterface()) {
            return new HashMap<>();
        }
        try {
            return encoderClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CodecConfigurationException(e.getMessage(), e);
        }
    }
}
