package com.alphadev.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;
import org.bukkit.entity.ArmorStand;

import java.util.List;
import java.util.UUID;

@Entity
public class WayStone {

    @Id
    private ObjectId id;

    @Indexed
    private String title;

    private UUID playerUUID;

    private String description;

    @Reference
    private House house;

    @Reference
    private CustomLocation locale;

    @Reference
    private CustomLocation spawn;

    @Reference
    private CustomLocation destiny;

    private ArmorStand wayStoneReferenceObject;

    @Reference
    private List<WayStone> connections;

    public ObjectId getId() {
        return id;
    }

    public WayStone setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public WayStone setHouse(House house) {
        this.house = house;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public WayStone setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomLocation getLocale() {
        return locale;
    }

    public WayStone setLocale(CustomLocation locale) {
        this.locale = locale;
        return this;
    }

    public CustomLocation getDestiny() {
        return destiny;
    }

    public WayStone setDestiny(CustomLocation destiny) {
        this.destiny = destiny;
        return this;
    }

    public List<WayStone> getConnections() {
        return connections;
    }

    public WayStone setConnections(List<WayStone> connections) {
        this.connections = connections;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WayStone setDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomLocation getSpawn() {
        return spawn;
    }

    public WayStone setSpawn(CustomLocation spawn) {
        this.spawn = spawn;
        return this;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public WayStone setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
        return this;
    }

    public ArmorStand getWayStoneReferenceObject() {
        return wayStoneReferenceObject;
    }

    public WayStone setWayStoneReferenceObject(ArmorStand wayStoneReferenceObject) {
        this.wayStoneReferenceObject = wayStoneReferenceObject;
        return this;
    }
}
