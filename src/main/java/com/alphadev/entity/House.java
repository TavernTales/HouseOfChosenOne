package com.alphadev.entity;

import dev.morphia.annotations.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

@Entity("house")
public class House  {
    @Id
    private Long id;
    @Indexed
    private String name;
    private String tag;
    private String details;
    private String policy;
    private String align;
    private List<String> ally;
    private List<String> neutral;
    private List<String> enemy;
    private String objective;
    private List<String> permissions;
    private Integer contribuition;

    private String worldName;
    private Double x;
    private Double y;
    private Double z;
    private Float yaw;
    private Float pitch;


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
        ally = configFileSections.getStringList("houses." + houseName + ".ally");
        neutral = configFileSections.getStringList("houses." + houseName + ".neutral");
        enemy = configFileSections.getStringList("houses." + houseName + ".enemy");
        objective = Objects.requireNonNull(configFileSections.getString("houses." + houseName + ".objective"));
        permissions = configFileSections.getStringList("houses." + houseName + ".permissions");

        if(configFileSections.get("houses." + houseName + ".location") != null){
            worldName = getWorldFromConfigFile(configFileSections, houseName);
            x = getCoordsLocationFromConfigFile(configFileSections, houseName, "x");
            y = getCoordsLocationFromConfigFile(configFileSections, houseName, "y");
            z = getCoordsLocationFromConfigFile(configFileSections, houseName, "z");
            yaw = getRotationLocationFromConfigFile(configFileSections, houseName, "yaw");
            pitch = getRotationLocationFromConfigFile(configFileSections, houseName, "pitch");
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

    public List<String> getAlly() {
        return ally;
    }

    public House setAlly(List<String> ally) {
        this.ally = ally;
        return this;
    }

    public List<String> getNeutral() {
        return neutral;
    }

    public House setNeutral(List<String> neutral) {
        this.neutral = neutral;
        return this;
    }

    public List<String> getEnemy() {
        return enemy;
    }

    public House setEnemy(List<String> enemy) {
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
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public House setLocation(Location location) {
        if(location == null) {
            worldName = null;
            x = null;
            y = null;
            z = null;
            yaw = null ;
            pitch = null;
            return this;
        }
        worldName = location.getWorld().getName();
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        yaw= location.getYaw();
        pitch = location.getPitch();

        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public House setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Long getId() {
        return id;
    }

    public House setId(Long id) {
        this.id = id;
        return  this;
    }

    public Integer getContribuition() {
        return contribuition;
    }

    public House setContribuition(Integer contribuition) {
        this.contribuition = contribuition;
        return this;
    }
}
