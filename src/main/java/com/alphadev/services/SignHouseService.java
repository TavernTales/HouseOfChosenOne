package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class SignHouseService {

    private static final String DIVISOR_STRING = "====================================";

    public static void signPlaceHouseSetup(SignChangeEvent event){

        if(!event.getPlayer().hasPermission("op"))
            return;

       switch (event.getLine(0).toLowerCase()){
            case "zeronia" -> {
                event.setLine(0, ChatColorUtil.boldText("Zeronia", ChatColor.RED));
                event.setLine(1, ChatColorUtil.boldText("Informa\u00E7\u00E3o", ChatColor.WHITE));
                event.setLine(2, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
            }
            case "vlarola" -> {
                event.setLine(0, ChatColorUtil.boldText("Vlarola", ChatColor.BLUE));
                event.setLine(1, ChatColorUtil.boldText("Informa\u00E7\u00E3o", ChatColor.WHITE));
                event.setLine(2, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
            }
            case "frandhra" -> {
                event.setLine(0, ChatColorUtil.boldText("Frandhra", ChatColor.GREEN));
                event.setLine(1, ChatColorUtil.boldText("Informa\u00E7\u00E3o", ChatColor.WHITE));
                event.setLine(2, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
            }
            case "nashor" -> {
                event.setLine(0, ChatColorUtil.boldText("Nashor", ChatColor.DARK_RED));
                event.setLine(1, ChatColorUtil.boldText("Informa\u00E7\u00E3o", ChatColor.WHITE));
                event.setLine(2, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
            }
            case "meia noite" -> {
                event.setLine(0, ChatColorUtil.boldText("Meia Noite", ChatColor.BLACK));
                event.setLine(1, ChatColorUtil.boldText("Informa\u00E7\u00E3o", ChatColor.WHITE));
                event.setLine(2, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
            }
            default -> {

            }
        }
    }

    public static void signHouseInteract(PlayerInteractEvent event){

      if(!event.hasBlock())
          return;

      if(!(event.getClickedBlock().getState() instanceof Sign sign))
          return;

      Player player = event.getPlayer();

      ConfigurationSection configFile = HouseOfChosenOne.getConfigFile();

      switch (ChatColor.stripColor(sign.getLine(0)).toLowerCase()){
          case "zeronia" -> sendMessageHouseDetails(player,new House(configFile , "zeronia"), ChatColor.RED, "zeronia");
          case "vlarola" -> sendMessageHouseDetails(player,new House(configFile , "vlarola"), ChatColor.BLUE, "vlarola");
          case "frandhra" -> sendMessageHouseDetails(player,new House(configFile , "frandhra"), ChatColor.DARK_GREEN, "frandhra");
          case "nashor" -> sendMessageHouseDetails(player,new House(configFile , "nashor"), ChatColor.DARK_RED, "nashor");
          case "meia noite" -> sendMessageHouseDetails(player,new House(configFile , "midnight-hunters"), ChatColor.DARK_GRAY, "midnight-hunters");
          default -> {}
      }
    }

    private static void sendMessageHouseDetails(Player player, House house, ChatColor houseColor, String houseName){
        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING,ChatColor.GREEN));
        player.sendMessage(ChatColorUtil.boldText(house.getHouse(),houseColor));
        player.sendMessage("");
        player.sendMessage( ChatColorUtil.boldText("Detalhes: ")+ChatColor.RESET+ house.getDetails());
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Tend\u00EAncia: ")+ChatColor.RESET+  house.getAlign());
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Pol\u00EDtica: ")+ChatColor.RESET+  house.getPolicy());
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Objetivos: ")+ChatColor.RESET+  house.getObjective());
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Rela\u00E7\u00F5es:"));
        player.sendMessage(" aliados: "+ ChatColorUtil.textColor(house.getAlly().toString(), ChatColor.GREEN));
        player.sendMessage(" neutros: "+ ChatColorUtil.textColor(house.getNeutral().toString(), ChatColor.YELLOW));
        player.sendMessage(" inimigos: "+ ChatColorUtil.textColor(house.getEnemy().toString(), ChatColor.RED));
        player.sendMessage("");

        TextComponent message = new TextComponent("CLIQUE PARA ENTRAR NA CASA");
        message.setBold(true);
        message.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        message.setUnderlined(true);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/houseofchosenone join "+houseName));

        player.spigot().sendMessage(message);
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING,ChatColor.GREEN));
    }

}
