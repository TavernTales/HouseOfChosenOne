package com.alphadev.entity;
import dev.morphia.annotations.Entity;

@Entity
public class HouseLocation{
    private String world;

    private double x;
    private double y;

    private double z;

    private float pitch;
    private float yaw;

    public HouseLocation() {
    }

    public HouseLocation(double x, double y, double z, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public String getWorldRef() {
        return world;
    }

    public HouseLocation setWorld(String world) {
        this.world = world;
        return this;
    }

    public double getX() {
        return x;
    }

    public HouseLocation setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public HouseLocation setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public HouseLocation setZ(double z) {
        this.z = z;
        return this;
    }

    public float getPitch() {
        return pitch;
    }

    public HouseLocation setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public float getYaw() {
        return yaw;
    }

    public HouseLocation setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }
}