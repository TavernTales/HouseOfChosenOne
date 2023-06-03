package com.alphadev.entity;
import dev.morphia.annotations.Entity;
import org.bukkit.Location;

@Entity
public class CustomLocation {
    private String world;

    private double x;
    private double y;

    private double z;

    private float pitch;
    private float yaw;

    public CustomLocation() {
    }

    public CustomLocation(double x, double y, double z, float pitch, float yaw, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.world = world;
    }


    public CustomLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        this.world = location.getWorld().getName();
    }


    public String getWorldRef() {
        return world;
    }

    public CustomLocation setWorld(String world) {
        this.world = world;
        return this;
    }

    public double getX() {
        return x;
    }

    public CustomLocation setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public CustomLocation setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public CustomLocation setZ(double z) {
        this.z = z;
        return this;
    }

    public float getPitch() {
        return pitch;
    }

    public CustomLocation setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public float getYaw() {
        return yaw;
    }

    public CustomLocation setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }
}