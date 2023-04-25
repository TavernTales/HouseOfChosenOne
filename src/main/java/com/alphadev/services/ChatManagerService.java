package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.utils.ChatColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public class ChatManagerService {

    private ChatManagerService(){}
    private static final Set<UUID> PLAYERS_IN_GLOBAL_CHAT = new HashSet<>();

    private static final ConfigurationSection CHAT_SETTINGS = HouseOfChosenOne.getConfigFile().getConfigurationSection("chat-settings");
    public static final Long LOCAL_DISTANCE_LIMIT;
    private static final HashMap<UUID, Player> PLAYER_REPLY = new HashMap<>();

    static {
        assert CHAT_SETTINGS != null;
        LOCAL_DISTANCE_LIMIT = CHAT_SETTINGS.getLong("distance-chat-local-limit");
    }
    public static void addLastPlayerToReplay(Player sender, Player receiver){
        PLAYER_REPLY.put(receiver.getUniqueId(), sender);
    }
    public static Player getLastPlayerToReply(Player player){
        return PLAYER_REPLY.get(player.getUniqueId());
    }
    public static void addPlayerToGlobalChat(Player player){
        PLAYERS_IN_GLOBAL_CHAT.add(player.getUniqueId());
    }
    public static void removePlayerToGlobalChat(Player player){
        PLAYERS_IN_GLOBAL_CHAT.remove(player.getUniqueId());
    }

    public static Set<UUID> getPlayersInGlobalChat(){
        return  PLAYERS_IN_GLOBAL_CHAT;
    }

    public static void onPlayerReceiveMessages(AsyncPlayerChatEvent event){
        Player sender = event.getPlayer();
        event.setFormat(getMessagePrefix(sender) + sender.getPlayerListName() + ChatColorUtil.textColor(" >> ", ChatColor.GREEN) + ChatColor.translateAlternateColorCodes('&',event.getMessage()));

        final Iterator<Player> iterator = event.getRecipients().iterator();
        while (iterator.hasNext()) {
            final Player recipient = iterator.next();

            boolean localLimite = chatLocalIsLimiteOutOfBoundsOrWorld(recipient, sender);

            if(playerHasOpOrPermission(recipient) && localLimite ){
                recipient.sendMessage(ChatColor.GRAY + ChatColor.stripColor(event.getFormat()));
                iterator.remove();
                continue;
            }

            if(localLimite){
                iterator.remove();
            }
        }
    }

    public  static  void onPlayerQuitEvent(PlayerQuitEvent event){
        removePlayerToGlobalChat(event.getPlayer());
    }


    public static String getMessagePrefix(Player player){
        if(getPlayersInGlobalChat().contains(player.getUniqueId()))
            return ChatColorUtil.textColor("Global", ChatColor.GREEN)+" | ";
        
        return ChatColorUtil.textColor("Local", ChatColor.GRAY)+" | ";
    }
    public static boolean chatLocalIsLimiteOutOfBoundsOrWorld(Player player, Player sender){
        if(getPlayersInGlobalChat().contains(sender.getUniqueId()))
            return  false;
        return !sender.getWorld().getName().equalsIgnoreCase(player.getWorld().getName()) || player.getLocation().distance(sender.getLocation()) > LOCAL_DISTANCE_LIMIT;
    }

    public static boolean playerHasOpOrPermission(Player player){
        return player.hasPermission("hco.admin");
    }

    public static void onPlayerJoinEvent(PlayerJoinEvent event) {
        new BukkitRunnable() {
            public void run() {
                event.getPlayer().sendMessage(ChatColorUtil.textColor("Use /global para acessar o chat global e /local para voltar ao chat local", ChatColor.GREEN));
            }
        }.runTaskLaterAsynchronously(HouseOfChosenOne.getPlugin(),20*10);
    }
}
