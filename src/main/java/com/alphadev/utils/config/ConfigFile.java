package com.alphadev.utils.config;

import com.alphadev.HouseOfChosenOne;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigFile {
    private final Plugin plugin = HouseOfChosenOne.getPlugin();
    private final File configFile = new File(plugin.getDataFolder(), "config.yml");
    private  FileConfiguration configFileConfiguration = new YamlConfiguration();

    public ConfigFile() {
        createConnectionConfig();
    }

    private void createConnectionConfig() {
        HouseOfChosenOne.logInfo("[HouseOfChosenOne] Config file . . .");

        try {
            if (!configFile.exists()) {
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating config file . . .");

                configFileConfiguration.save(configFile);

                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Config file configuration: 100%");
                HouseOfChosenOne.logInfo("[HouseOfChosenOne] Config File: 100%");
            }
            configFileConfiguration = YamlConfiguration.loadConfiguration(configFile);

            createConfigSection();

            configFileConfiguration.save(configFile);

        } catch (Exception e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Config file configuration error:\n" + e.getMessage(),e);
        }
    }

    private void createConfigSection() throws IOException {

        ConfigurationSection settingsSection = configFileConfiguration.getConfigurationSection("settings");

        ConfigurationSection configSection = configFileConfiguration.getConfigurationSection("houses");


        if(settingsSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Settings File Configuration . . .");
            settingsSection = configFileConfiguration.createSection("settings");
        }

        if (configSection == null){
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Creating Config File Configuration . . .");
            configSection = configFileConfiguration.createSection("houses");
        }

        settingsSection.set("api-grief-prevention-block-bonus", 3000);


       if( configSection.getConfigurationSection("zeronia") == null)
            configSection.createSection("zeronia");
       if( configSection.getConfigurationSection("vlarola") == null)
            configSection.createSection("vlarola");
        if( configSection.getConfigurationSection("frandhra") == null)
            configSection.createSection("frandhra");
        if( configSection.getConfigurationSection("nashor") == null)
            configSection.createSection("nashor");
        if( configSection.getConfigurationSection("drakkaris") == null)
            configSection.createSection("drakkaris");

        ConfigurationSection zenoniaKingdom = configSection.getConfigurationSection("zeronia");

        if (!zenoniaKingdom.contains("house"))
            zenoniaKingdom.set("house", "Reino de Zeronia");

        if (!zenoniaKingdom.contains("tag"))
            zenoniaKingdom.set("tag", "&cZeronia");

        if (!zenoniaKingdom.contains("details"))
            zenoniaKingdom.set("details", "Zeronia \u00E9 um Reino que se encontra nas Planicies e Florestas ao redor, seu simbolo \u00E9 relacionado ao sol enquanto o vermelho simboliza a paix\u00E3o pelo seu reino. Zeronia \u00E9 conhecida pelo bem estar de seu povo e desenvolvimento com outras Casas, apenas da rela\u00E7\u00E3o com algumas nao serem t\u00E3o positivas quando outras, entretanto, seu grande ponto forte sempre foi o comercio e a busca de grandes guerreiros para lutar em nome de Zeronia");

        if (!zenoniaKingdom.contains("policy"))
            zenoniaKingdom.set("policy", "Monarquia");

        if (!zenoniaKingdom.contains("align"))
            zenoniaKingdom.set("align", "Bom e Leal");

        if (!zenoniaKingdom.contains("ally"))
            zenoniaKingdom.set("ally", Arrays.asList("Vlarol\u00E1"));

        if (!zenoniaKingdom.contains("neutral"))
            zenoniaKingdom.set("neutral", Arrays.asList("Frandha"));

        if (!zenoniaKingdom.contains("enemy"))
            zenoniaKingdom.set("enemy", Arrays.asList("Nashor","Drakkaris"));

        if (!zenoniaKingdom.contains("objective"))
            zenoniaKingdom.set("objective", "Com\u00E9rcio local e internacional, crescimento da civiliza\u00E7\u00E3o e batalhas PVP");

        if (!zenoniaKingdom.contains("contribuition"))
            zenoniaKingdom.set("contribuition", 0);

        if (!zenoniaKingdom.contains("permissions"))
            zenoniaKingdom.set("permissions", List.of());

        ConfigurationSection vlarolaCitadel = configSection.getConfigurationSection("vlarola");

        if (!vlarolaCitadel.contains("house"))
            vlarolaCitadel.set("house", "Cidade de Vlarol\u00E1");

        if (!vlarolaCitadel.contains("tag"))
            vlarolaCitadel.set("tag", "&bVlarol\u00E1");

        if (!vlarolaCitadel.contains("details"))
            vlarolaCitadel.set("details", "A Cidade de Vlarol\u00E1 se encontra nos grandes Desertos, sua bandeira simboliza o conhecimento e constru\u00E7\u00E3o. Vlarol\u00E1 possui um forte vinculo com Zeronia, principalmente no comercio de materias para constru\u00E7\u00F5es e alimentos, entretanto, nada impede de fazer vinculos com outras casas para o bem de sua civiliza\u00E7\u00E3o, o foco da Cidade \u00E9 ser extremamente avan\u00E7ado na Tecnologia de Redstone e constru\u00E7\u00F5es, alem de uma grande produ\u00E7\u00E3o de comida e alimento para comercio.");

        if (!vlarolaCitadel.contains("policy"))
            vlarolaCitadel.set("policy", "Democracia");

        if (!vlarolaCitadel.contains("align"))
            vlarolaCitadel.set("align", "Neutro Bom");

        if (!vlarolaCitadel.contains("ally"))
            vlarolaCitadel.set("ally", Arrays.asList("Zeronia"));

        if (!vlarolaCitadel.contains("neutral"))
            vlarolaCitadel.set("neutral", Arrays.asList("Frandha","Nashor"));

        if (!vlarolaCitadel.contains("enemy"))
            vlarolaCitadel.set("enemy", Arrays.asList("Drakkaris"));

        if (!vlarolaCitadel.contains("objective"))
            vlarolaCitadel.set("objective", "Com\u00E9rcio local e internacional, crescimento da civiliza\u00E7\u00E3o e batalhas PVP");

        if (!vlarolaCitadel.contains("contribuition"))
            vlarolaCitadel.set("contribuition", 0);

        if (!vlarolaCitadel.contains("permissions"))
            vlarolaCitadel.set("permissions", List.of());

        ConfigurationSection frandhraMonastery = configSection.getConfigurationSection("frandhra");

        if (!frandhraMonastery.contains("house"))
            frandhraMonastery.set("house", "Monast\u00E9rio dos Frandhra");

        if (!frandhraMonastery.contains("tag"))
            frandhraMonastery.set("tag", "&2Frandhra");

        if (!frandhraMonastery.contains("details"))
            frandhraMonastery.set("details", "O Monast\u00E9rio se encontra nos campos gelados da Taiga, sua bandeira simboliza um grande Drag\u00E3o. Frandhra possui uma forte cren\u00E7a nos Drag\u00F5es, criaturas ancestrais que dominaram o mundo com seu poder e magia, ainda hoje, eles acreditam fielmente na existencia do ultimo da especie, apesar de nao saberem aonde ele se encontra, por\u00E9m, com o conhecimento adquirido com o tempo, as po\u00E7\u00F5es e encantamentos foram o que mais destacaram o Imp\u00E9rio, a Magia!");

        if (!frandhraMonastery.contains("policy"))
            frandhraMonastery.set("policy", "Rep\u00FAblica");

        if (!frandhraMonastery.contains("align"))
            frandhraMonastery.set("align", "Neutro Puro");

        if (!frandhraMonastery.contains("ally"))
            frandhraMonastery.set("ally", List.of());

        if (!frandhraMonastery.contains("neutral"))
            frandhraMonastery.set("neutral", Arrays.asList("Vlarol\u00E1","Zeronia","Nashor","Drakkaris"));

        if (!frandhraMonastery.contains("enemy"))
            frandhraMonastery.set("enemy", List.of());

        if (!frandhraMonastery.contains("objective"))
            frandhraMonastery.set("objective", "Com\u00E9rcio de po\u00E7\u00F5es e Encantamentos, explora\u00E7\u00E3o em larga escala e batalhas PVP");

        if (!frandhraMonastery.contains("contribuition"))
            frandhraMonastery.set("contribuition", 0);

        if (!frandhraMonastery.contains("permissions"))
            frandhraMonastery.set("permissions", List.of());

        ConfigurationSection nashorImperio = configSection.getConfigurationSection("nashor");

        if (!nashorImperio.contains("house"))
            nashorImperio.set("house", "Imp\u00E9rio de Nashor");

        if (!nashorImperio.contains("tag"))
            nashorImperio.set("tag", "&4Nashor");

        if (!nashorImperio.contains("details"))
            nashorImperio.set("details", "O Imp\u00E9rio de encontra nas grandes montanhas, seu simbolo \u00E9 uma Fenix. O poderoso imp\u00E9rio de Nashor, nascidos e crescido nas batalhas, sua fonte indesejavel de poder atinge todos, e se precisar lutar e matar para conseguir seus objetivos, esse ser\u00E1 seu meio, com pouco objetivo de comercio e foco principal em domina\u00E7\u00E3o atraves de combate, transforma todos os seus membros em verdadeiras maquinas de guerra!");

        if (!nashorImperio.contains("policy"))
            nashorImperio.set("policy", "Imperialista");

        if (!nashorImperio.contains("align"))
            nashorImperio.set("align", "Mal e Leal");

        if (!nashorImperio.contains("ally"))
            nashorImperio.set("ally",Arrays.asList("Drakkaris"));

        if (!nashorImperio.contains("neutral"))
            nashorImperio.set("neutral", Arrays.asList("Vlarol\u00E1","Frandha"));

        if (!nashorImperio.contains("enemy"))
            nashorImperio.set("enemy", Arrays.asList("Zeronia"));

        if (!nashorImperio.contains("objective"))
            nashorImperio.set("objective", "Explora\u00E7\u00E3o em larga escala e combates PVP");

        if (!nashorImperio.contains("contribuition"))
            nashorImperio.set("contribuition", 0);

        if (!nashorImperio.contains("permissions"))
            nashorImperio.set("permissions", List.of());

        ConfigurationSection drakkaris = configSection.getConfigurationSection("drakkaris");

        if (!drakkaris.contains("house"))
            drakkaris.set("house", "Guarni\u00E7\u00E3o Drakkaris");

        if (!drakkaris.contains("tag"))
            drakkaris.set("tag", "&8Drakkaris");

        if (!drakkaris.contains("details"))
            drakkaris.set("details", "Os Drakkaris se encontram nos Pantanos, seu simbolo j\u00E1 diz por si s\u00F3 o que eles gostam de fazer. Os Drakkaris s\u00E3o extremamente individualistas, n\u00E3o se importanto com quem esta ao seu lado ou quem est\u00E1 do outro lado, nem se v\u00E3o parar na pris\u00F5es ou executados, um bando de piratas aonde o saque e a ca\u00E7a s\u00E3o sua principal fonte de renda, apesar de n\u00E3o possuirem comercio e nem uma larga constru\u00E7\u00E3o como as casas rivais, eles s\u00E3o especialistas na espionagem, na ca\u00E7a, e no saque, sendo contratados para fazer suas fun\u00E7\u00F5es, mesmo que seu alvo seja membro da propria Guilda");

        if (!drakkaris.contains("policy"))
            drakkaris.set("policy", "Anarquia");

        if (!drakkaris.contains("align"))
            drakkaris.set("align", "Caotico Mal");

        if (!drakkaris.contains("ally"))
            drakkaris.set("ally",Arrays.asList("Nashor"));

        if (!drakkaris.contains("neutral"))
            drakkaris.set("neutral",Arrays.asList("Frandha"));

        if (!drakkaris.contains("enemy"))
            drakkaris.set("enemy", Arrays.asList("Vlarol\u00E1","Zeronia"));

        if (!drakkaris.contains("objective"))
            drakkaris.set("objective", "Saques de pessoas desprevinidas, Assasinatos por contrato e Batalhas PVP");

        if (!drakkaris.contains("contribuition"))
            drakkaris.set("contribuition", 0);

        if (!drakkaris.contains("permissions"))
            drakkaris.set("permissions", List.of());

        configFileConfiguration.save(configFile);
    }

    public void createLocationSection(String houseName, Location location ){

        ConfigurationSection configSection = configFileConfiguration.getConfigurationSection("houses");
        ConfigurationSection houseSection =  configSection.getConfigurationSection(houseName);

        houseSection.set("location.world-name", location.getWorld().getName());
        houseSection.set("location.x", location.getX());
        houseSection.set("location.y", location.getY());
        houseSection.set("location.z", location.getZ());
        houseSection.set("location.yaw",location.getYaw());
        houseSection.set("location.pitch",location.getPitch());

        try {
            configFileConfiguration.save(configFile);
        } catch (IOException e) {
            HouseOfChosenOne.logInfo("[HouseOfChosenOne] Config file configuration error location:\n" + e.getMessage(),e);
        }
    }



    public  File getFile() {
        return configFile;
    }

    public FileConfiguration getConfig() {
        return configFileConfiguration;
    }

}
