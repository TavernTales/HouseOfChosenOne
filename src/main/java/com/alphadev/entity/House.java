package com.alphadev.entity;

import dev.morphia.annotations.*;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

@Entity("house")
public class House  {

    @Id
    private ObjectId id;
    @Indexed
    private String name;
    private String tag;
    private String details;
    private String policy;
    private String align;
    @Reference
    private List<House> ally;
    @Reference
    private List<House> neutral;
    @Reference
    private List<House> enemy;
    private String objective;
    private List<String> permissions;
    private Integer contribution;
    private HouseLocation reg;

    public House() {
    }
    public House(ConfigurationSection configFileSections, String houseName) {

        if(configFileSections.get("houses." + houseName) == null)
            return;

        name = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".house"));
        tag = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".tag"));
        details = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".details"));
        policy = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".policy"));
        align = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".align"));
        objective = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".objective"));
        permissions = configFileSections.getStringList("houses." + houseName + ".permissions");

        if(configFileSections.get("houses." + houseName + ".location") != null){
            reg.setWorld(null);
            reg.setX((double) getCoordsLocationFromConfigFile(configFileSections, houseName, "x"));
            reg.setY((double)getCoordsLocationFromConfigFile(configFileSections, houseName, "y"));
            reg.setZ((double)getCoordsLocationFromConfigFile(configFileSections, houseName, "z"));
            reg.setYaw((float) getRotationLocationFromConfigFile(configFileSections, houseName, "yaw"));
            reg.setPitch((float) getRotationLocationFromConfigFile(configFileSections, houseName, "pitch"));
        }

    }
    private String getWorldFromConfigFile(ConfigurationSection configFileSections, String houseName){
        return Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".location.world-name"));
    }

    private double getCoordsLocationFromConfigFile(ConfigurationSection configFileSections, String houseName, String coord){
        return Double.parseDouble((Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".location."+coord))));
    }
    private float getRotationLocationFromConfigFile(ConfigurationSection configFileSections, String houseName, String coord){
        return Float.parseFloat((Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".location."+coord))));
    }

    public ObjectId getId() {
        return id;
    }

    public House setId(ObjectId id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public House setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public House setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getPolicy() {
        return policy;
    }

    public House setPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public String getAlign() {
        return align;
    }

    public House setAlign(String align) {
        this.align = align;
        return this;
    }

    public List<House> getAlly() {
        return ally;
    }

    public House setAlly(List<House> ally) {
        this.ally = ally;
        return this;
    }

    public List<House> getNeutral() {
        return neutral;
    }

    public House setNeutral(List<House> neutral) {
        this.neutral = neutral;
        return this;
    }

    public List<House> getEnemy() {
        return enemy;
    }

    public House setEnemy(List<House> enemy) {
        this.enemy = enemy;
        return this;
    }

    public String getObjective() {
        return objective;
    }

    public House setObjective(String objective) {
        this.objective = objective;
        return this;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(reg.getWorldRef()), reg.getX(), reg.getY(), reg.getZ(), reg.getYaw(), reg.getPitch());
    }

    public House setLocation(HouseLocation reg) {
        if(reg == null) {
            return this;
        }
        this.reg = reg;
        return this;
    }

    public Integer getContribution() {
        return contribution;
    }

    public House setContribution(Integer contribution) {
        this.contribution = contribution;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public House setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }
}
