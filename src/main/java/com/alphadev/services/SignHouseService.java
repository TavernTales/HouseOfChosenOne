package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.commands.services.AdminCommandService;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;



public class SignHouseService {
    private  SignHouseService(){}
    private static final String DIVISOR_STRING = "====================================";

    public static void signPlaceHouseSetup(SignChangeEvent event){

        if(!AdminCommandService.playerHasAdminPermission(event.getPlayer()))
            return;

       switch (event.getLine(0).toLowerCase()){
            case "zeronia" -> configureSign(event,"Zeronia", ChatColor.RED);
            case "vlarola" -> configureSign(event,"Vlarola", ChatColor.BLUE);
            case "frandhra" -> configureSign(event,"Frandhra", ChatColor.GREEN);
            case "nashor" ->  configureSign(event,"Nashor", ChatColor.DARK_RED);
            case "drakkaris" -> configureSign(event,"Drakkaris", ChatColor.DARK_GRAY);
        }
    }

    private static void configureSign(SignChangeEvent event, String houseName, ChatColor houseColor){
        event.setLine(0, ChatColorUtil.boldText(houseName, houseColor));
        event.setLine(1, ChatColorUtil.textColor("Informa\u00E7\u00E3o", ChatColor.WHITE));
        event.setLine(2, "");
        event.setLine(3, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
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
          case "drakkaris" -> sendMessageHouseDetails(player,new House(configFile , "drakkaris"), ChatColor.DARK_GRAY, "drakkaris");
      }
    }

    private static void sendMessageHouseDetails(Player player, House house, ChatColor houseColor, String houseName){

        for (int i = 0; i < 20; i++) {
            player.sendMessage("");
        }

        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING,ChatColor.GREEN));
        player.sendMessage(ChatColorUtil.boldText(house.getName(),houseColor));
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
        message.setColor(net.md_5.bungee.api.ChatColor.AQUA);
        message.setUnderlined(true);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/houseofchosenone join "+houseName));

        player.spigot().sendMessage(message);
        player.sendMessage("");
        player.sendMessage(ChatColor.RED+" Aviso: ao selecionar a casa voc\u00EA ser\u00E1 teleportado para a capital, caso queira trocar de casa sofrer\u00E1 uma penalidade de 48Horas para selecionar uma nova casa e perder\u00E1 o seu progresso. '/hco leave'");
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING,ChatColor.GREEN));
    }

}
