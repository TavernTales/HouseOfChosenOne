package com.alphadev.entity;

import com.alphadev.HouseOfChosenOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

public class House {
    private String house;
    private String details;
    private String policy;
    private String align;
    private List<String> ally;
    private List<String> neutral;
    private List<String> enemy;
    private String objective;
    private Location location;

    private List<String> permissions;

    public House() {
    }
    public House(ConfigurationSection configFileSections, String houseName) {

        if(configFileSections.get("houses." + houseName) == null)
            return;

        house = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".house"));
        details = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".details"));
        policy = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".policy"));
        align = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".align"));
        ally = configFileSections.getStringList("houses." + houseName + ".ally");
        neutral = configFileSections.getStringList("houses." + houseName + ".neutral");
        enemy = configFileSections.getStringList("houses." + houseName + ".enemy");
        objective = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".objective"));
        permissions = configFileSections.getStringList("houses." + houseName + ".permissions");

        if(configFileSections.get("houses." + houseName + ".location") != null)
         location = new Location(
                getWorldFromConfigFile(configFileSections, houseName),
                getCoordsLocationFromConfigFile(configFileSections, houseName, "x"),
                getCoordsLocationFromConfigFile(configFileSections, houseName, "y"),
                getCoordsLocationFromConfigFile(configFileSections, houseName, "z")
        );
    }
    private World getWorldFromConfigFile(ConfigurationSection configFileSections, String houseName){
        return Bukkit.getWorld(Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".location.world-name")));
    }

    private double getCoordsLocationFromConfigFile(ConfigurationSection configFileSections, String houseName, String coord){
        return Double.parseDouble((Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".location."+coord))));
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public List<String> getAlly() {
        return ally;
    }

    public void setAlly(List<String> ally) {
        this.ally = ally;
    }

    public List<String> getNeutral() {
        return neutral;
    }

    public void setNeutral(List<String> neutral) {
        this.neutral = neutral;
    }

    public List<String> getEnemy() {
        return enemy;
    }

    public void setEnemy(List<String> enemy) {
        this.enemy = enemy;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
