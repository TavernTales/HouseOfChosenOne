package com.alphadev.commands;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BasicCommand implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();

        if(args.length > 1 && args[0].equalsIgnoreCase("join")){

            House house = new House( HouseOfChosenOne.getConfigFile(), args[1]);

            if(house.getHouse() == null)
                return  false;

            ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);


            if(configurationSection != null && configurationSection.getString("house") != null){
                player.sendMessage(ChatColorUtil.boldText("Voc\u00EA j\u00E1 est\u00E1 em uma casa",ChatColor.RED));
                player.sendMessage("Execute o comando '/hco leave' para sair da sua casa atual. mas voc\u00EA perder\u00E1 todo o seu progresso.");
                return false;
            }

            if(configurationSection != null && configurationSection.getString("reset") != null){
                long resetTimeMillis =  configurationSection.getLong("reset");
                long currentTimeMillis = System.currentTimeMillis();

                if(resetTimeMillis > currentTimeMillis ){
                    player.sendMessage(ChatColorUtil.boldText("Voc\u00EA ainda n\u00E3o pode entrar em uma nova casa",ChatColor.RED));
                    player.sendMessage("Tempo para entrar em uma casa nova \u00E9 de: "+ (resetTimeMillis - currentTimeMillis)  / 60 / 60 / 1000 + " horas" );
                    return false;
                }
            }

           HouseOfChosenOne.getPlayerConfig().createPlayersSection(player,args[1],house.getPermissions());
           player.sendMessage(ChatColorUtil.boldText("Parab\u00E9ns voc\u00EA entrou na casa "+ChatColor.RESET+ ChatColor.GREEN+house.getHouse()));
           return true;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("leave")){
            HouseOfChosenOne.getPlayerConfig().createResetSection(player);
            return true;
        }

        return false;
    }




}
