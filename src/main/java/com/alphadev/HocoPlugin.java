package com.alphadev;

import com.alphadev.commands.HocoMainCommand;
import com.alphadev.commands.MainCommand;
import com.alphadev.entities.HcoHouse;
import com.alphadev.listeners.HcoPlayerListeners;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;


public class HocoPlugin extends JavaPlugin {
    public static List<HcoHouse> hcoHouseConstantInfo;
    private Hoco hoco;
    private final PluginManager pluginManager = this.getServer().getPluginManager();
    private static MainCommand mainCommand;

    public static void broadcast(String msg) {
        HocoPlugin.getPlugin().getServer().broadcastMessage(msg);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        // Exibe o log das informações sobre o carregamento do plugin no console
        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));
        broadcast(ChatColorUtil.boldText("[HocoPlugin] ", ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Loaded Version: " + getPlugin().getDescription().getVersion(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Author: " + getPlugin().getDescription().getAuthors(), ChatColor.GREEN));
        broadcast(ChatColorUtil.textColor(" Description: \n " + ChatColor.GRAY + getPlugin().getDescription().getDescription(), ChatColor.GREEN));
        broadcast(ChatColorUtil.boldText("======================", ChatColor.GOLD));

        // Salva as configurações padrões do plugin
        this.saveConfig();
        // Carrega informações constantes das casas.
        hcoHouseConstantInfo = HelpUtils.loadHouseConstants();
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new HcoPlayerListeners(this),this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.hoco = new Hoco(this);
        this.registerListeners();
        // Registra os event listeners do plugin.
        mainCommand = new HocoMainCommand();
        mainCommand.registerMainCommand(this,"hoco");
        // Obtém a instância do banco de dados Mongo e o Datastore.
    }

    @Override
    public void onDisable() {
        super.onDisable();
        // Cancela todas as taks e remove todos os event listeners registrados.
        Bukkit.getServer().getScheduler().cancelTasks(this);
        HandlerList.unregisterAll();
        hoco.getConnection().close();
    }
    // Retornar a instância do Hoco
    public Hoco getInstance() {
        return hoco;
    }
    // Retorna a instância do plugin
    public static Plugin getPlugin() {
        return getPlugin(HocoPlugin.class);
    }
}

