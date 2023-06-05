package com.alphadev;

import com.alphadev.commands.HocoMainCommand;
import com.alphadev.commands.MainCommand;
import com.alphadev.entities.HCOHouse;
import com.alphadev.listeners.PlayerListeners;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;


public class HOCOPlugin extends JavaPlugin {
    public static List<HCOHouse> hcoHouseConstantInfo;
    private final PluginManager pluginManager = this.getServer().getPluginManager();
    private static MainCommand mainCommand;

    public static void broadcast(String msg) {
        HOCOPlugin.getPlugin().getServer().broadcastMessage(msg);
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
        pluginManager.registerEvents(new PlayerListeners(),this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        HOCOProvider.getInstance();
        this.registerListeners();
        // Registra os event listeners do plugin.
        mainCommand = new HocoMainCommand();
        mainCommand.registerMainCommand(this,"hoco");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        // Cancela todas as taks e remove todos os event listeners registrados.
        Bukkit.getServer().getScheduler().cancelTasks(this);
        HandlerList.unregisterAll();
        HOCOProvider.getInstance().getConnection().close();
    }

    // Retorna a instância do plugin
    public static Plugin getPlugin() {
        return getPlugin(HOCOPlugin.class);
    }
}

