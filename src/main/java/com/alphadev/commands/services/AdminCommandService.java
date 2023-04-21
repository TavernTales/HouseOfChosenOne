package com.alphadev.commands.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AdminCommandService {

    private AdminCommandService(){}

    private static final String NO_PERMISSION_MESSAGE = "Voc\u00EA n\u00E3o tem permiss\u00E3o para executar esse comando";

    public static boolean playerHasAdminPermission(Player player){

          if(!player.hasPermission("hco.admin")){
              player.sendMessage(NO_PERMISSION_MESSAGE);
              return  false;
          }

        return true ;
    }

    public static boolean playerLoadHouseOfChosenOne(Player player, Command command, String[] args){

        if(!command.getName().equalsIgnoreCase("houseofchosenone") || args == null ||  args.length <= 0 || !(args[0].equalsIgnoreCase("load") || args[0].equalsIgnoreCase("reload")))
            return false;

        if(!playerHasAdminPermission(player))
            return  false;

        HouseOfChosenOne.loadPlayerStats();
        player.sendMessage("[HCO] Configura\u00E7\u00F5es recarregadas ...");
        return true;
    }

    public  static boolean playerChangeHouseTag(Player player, Command command, String[] args){
        if( !command.getName().equalsIgnoreCase("houseofchosenone") || args == null ||args.length <= 0 || !args[0].equalsIgnoreCase("tag"))
            return  false;

        if(!playerHasAdminPermission(player))
            return  false;

        if(args[1] == null || args[1].isEmpty() || !HelpUtils.HOUSES.contains(args[1])){
            player.sendMessage("Nome da casa inv\u00E1lido");
            return  false;
        }

        if( args.length < 3 || args[2] == null || args[2].isEmpty() || Bukkit.getServer().getOnlinePlayers().stream().anyMatch(pl -> pl.getName().equalsIgnoreCase(args[2]))){
            player.sendMessage("Tag inv\u00E1lida");
            return  false;
        }

        new ConfigFile().changeTag(args[2], args[1]);
        HouseOfChosenOne.loadPlayerStats();

        player.sendMessage("Tag de "+ args[1] + " alterada para "+ ChatColor.translateAlternateColorCodes('&', args[2]));
        return true;
    }

    public static boolean citadelDefineCommand(Player player, Command command, String[] args){

        if(!command.getName().equalsIgnoreCase("citadel") || args == null || args.length == 0 || !args[0].equalsIgnoreCase("set") ){
            return false;
        }

       if(!houseCommandValidations(player, args))
           return false;

        House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

        if(house.getName() == null){
            player.sendMessage(ChatColor.RED+" Nome da casa inv\u00E1lido tente /city set <nome da casa>");
            player.sendMessage(ChatColor.RED+" casas dispon\u00EDveis: "+ HelpUtils.HOUSES.toString());
            return  false;
        }

        HouseOfChosenOne.createHouseLocation(args[1].toLowerCase(), Objects.requireNonNull(player).getLocation());
        player.sendMessage(ChatColor.AQUA+" Coordenadas adicionadas a casa "+args[1]);

        return true;
    }

    public static boolean checkIfHouseNameExists(String houseName){
        return !HelpUtils.isNullOrEmpty(houseName) && HelpUtils.HOUSES.contains(houseName.toLowerCase());
    }

    public static boolean houseCommandValidations(Player player , String... args){

        if(HelpUtils.isNullOrEmpty(args) || args.length < 2 || !checkIfHouseNameExists(args[1])){
            player.sendMessage(ChatColor.RED+" casas dispon\u00EDveis: zeronia, vlarola, frandhra, nashor, drakkaris");
            return  false;
        }
        return  true;
    }

}
