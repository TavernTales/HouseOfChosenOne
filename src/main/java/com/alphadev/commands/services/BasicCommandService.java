package com.alphadev.commands.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.House;
import com.alphadev.enums.HouseEnum;
import com.alphadev.events.quest.QuestStartEvent;
import com.alphadev.repository.HouseRepository;
import com.alphadev.repository.PlayerDataRepository;
import com.alphadev.services.ChatManagerService;
import com.alphadev.services.PlayerMoveService;
import com.alphadev.services.ScoreBoardService;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.config.ConfigFile;
import com.alphadev.utils.config.ConfigPlayers;
import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

public class BasicCommandService {


    private BasicCommandService(){}
    private static final HashMap<UUID, Integer> scheduleTaskPlayer = new HashMap<>();
    private static final long ELEVEN_SECONDS_IN_MILLIS = 11000;
    private static final long MILLIS_TO_SECONDS = 1000;
    private static final String HOUSE_CONSTANT = "house";
    private  static final String API_GRIEF_PREVENTION_BONUS = "api-grief-prevention-block-bonus";

    private static final HouseRepository houseRepository = new HouseRepository();
    private static final PlayerDataRepository playerDataRepository =  new PlayerDataRepository();
    public static boolean playerJoinInHouseCommand(Player player, Command command, String[] args){


        if(!command.getName().equalsIgnoreCase("houseofchosenone") || args.length  <= 0  || !args[0].equalsIgnoreCase("join")){
            return false;
        }

        if(!AdminCommandService.houseCommandValidations(player, args)){
            return  false;
        }

        Optional<House> house = houseRepository.findById(HouseEnum.fromName(args[1]).getId());
        if( house.isEmpty() || house.get().getName() == null)
            return  false;

        Optional<com.alphadev.entity.PlayerData> playerData = playerDataRepository.findById(player.getUniqueId());

        if(playerData.isPresent() && playerData.get().getHouse() != null && !playerData.get().getHouse().getName().isEmpty()){
            player.sendMessage(ChatColorUtil.boldText("Voc\u00EA j\u00E1 est\u00E1 em uma casa", ChatColor.RED));
            player.sendMessage("Execute o comando '/hco leave' para sair da sua casa atual. mas voc\u00EA perder\u00E1 todo o seu progresso e sofrer\u00E1 uma penalidade de 48Horas para entrar na pr\u00F3xima casa.");
            return false;
        }

        if(playerData.isPresent() && playerData.get().getResetTimeout() > 0){
            long resetTimeMillis =  playerData.get().getResetTimeout();
            long currentTimeMillis = System.currentTimeMillis();

            if(resetTimeMillis > currentTimeMillis ){
                player.sendMessage(ChatColorUtil.boldText("Voc\u00EA ainda n\u00E3o pode entrar em uma nova casa",ChatColor.RED));
                player.sendMessage("Tempo para entrar em uma casa nova \u00E9 de: "+ (resetTimeMillis - currentTimeMillis)  / 60 / 60 / 1000 + " horas" );
                return false;
            }
        }

        com.alphadev.entity.PlayerData data = new com.alphadev.entity.PlayerData();
        data.setUUID(player.getUniqueId())
                .setPlayerName(player.getName())
                .setHouse(house.get())
                .setResetTimeout(0L)
                .setContribuitions(0)
                .setPermissions(List.of());

        playerDataRepository.save(data);

        player.sendMessage(ChatColorUtil.boldText("Parab\u00E9ns voc\u00EA entrou na casa "+ChatColor.RESET+ ChatColor.GREEN+house.get().getName()));

        if(house.get().getLocation() != null)
            player.teleport(house.get().getLocation());

        player.sendTitle(ChatColorUtil.boldText(house.get().getName()),"",10,20,10);
        ScoreBoardService.setPlayerHouseScoreBoardTag(player);

        griefPreventionIntegrationSetClaimMBlockBonus(player, false);

        return true;
    }


    public static boolean playerTeleportToHouseLobby(Player player, Command command){

        if (!command.getName().equalsIgnoreCase("lobby"))
            return  false;

        PlayerMoveService.removePlayerTracker(player);

        final long startTime = System.currentTimeMillis();

        if(scheduleTaskPlayer.get(player.getUniqueId()) != null)
            Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));

        scheduleTaskPlayer.put(player.getUniqueId(),  Bukkit.getScheduler().scheduleSyncRepeatingTask(HouseOfChosenOne.getPlugin(), ()->{

            long endTime = System.currentTimeMillis();
            long secondsRemaning = ((startTime + ELEVEN_SECONDS_IN_MILLIS  ) - endTime) / MILLIS_TO_SECONDS;

            player.sendTitle("Retornando ao Lobby","N\u00E3o se mova "+secondsRemaning+" Segundos",5 , 20, 5);

            if(secondsRemaning <= 0 ){
                ConfigurationSection configurationSection = new ConfigPlayers().getConfiguration(player);
                if(configurationSection != null && configurationSection.getString(HOUSE_CONSTANT) != null) {
                    House house = new House(HouseOfChosenOne.getConfigFile(), configurationSection.getString(HOUSE_CONSTANT));
                    player.teleport(house.getLocation());
                    player.sendTitle(ChatColorUtil.boldText(house.getName()),"",10,20,10);
                }
                Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));
            }

            if(  PlayerMoveService.isPlayerInMovement(player)){
                player.sendTitle("Teleport Cancelado","",10 , 20, 10);
                Bukkit.getScheduler().cancelTask(scheduleTaskPlayer.get(player.getUniqueId()));
            }

        }, 0L,20L));

        return true;
    }


    public static boolean playerLeaveHouse(Player player, Command command, String[] args){

        if(!command.getName().equalsIgnoreCase("houseofchosenone") || args == null || args.length <= 0 || !args[0].equalsIgnoreCase("leave"))
            return  false;

        Optional<com.alphadev.entity.PlayerData> playerData = playerDataRepository.findById(player.getUniqueId());
        if(playerData.isEmpty() || HelpUtils.isNullOrEmpty(playerData.get().getHouse())) {
            return  false;
        }

        player.sendMessage(ChatColorUtil.boldText("Voc\u00EA deixou sua casa "));
        playerDataRepository.save(playerData.get().setResetTimeout(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000));

        ScoreBoardService.removePlayerFromHouseTeam(player, playerData.get().getHouse());

        griefPreventionIntegrationSetClaimMBlockBonus(player, true);

        return true;
    }


    private static void griefPreventionIntegrationSetClaimMBlockBonus(Player player, boolean remove){
        if(HouseOfChosenOne.getPlugin().getServer().getPluginManager().getPlugin("GriefPrevention") == null)
            return;

        GriefPrevention griefPrevention =  GriefPrevention.instance;

        if(griefPrevention != null ) {
            ConfigurationSection settingsFile = new ConfigFile().getConfig().getConfigurationSection("settings");

            if(settingsFile != null && settingsFile.getInt(API_GRIEF_PREVENTION_BONUS) > 0){
                DataStore dataStore = griefPrevention.dataStore;
                PlayerData playerData = dataStore.getPlayerData(player.getUniqueId());

                if(!remove)
                    playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() + settingsFile.getInt(API_GRIEF_PREVENTION_BONUS));
                else
                    playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() - settingsFile.getInt(API_GRIEF_PREVENTION_BONUS));

                dataStore.savePlayerData(player.getUniqueId(), playerData);
            }
        }
    }

    public static boolean questTest(Player sender, Command command, String... args){
        if(!command.getName().equalsIgnoreCase("questTest") || !args[0].equalsIgnoreCase("start"))return false;
        Bukkit.getScheduler().runTaskAsynchronously(HouseOfChosenOne.getPlugin(), () -> Bukkit.getPluginManager().callEvent(new QuestStartEvent(sender)));
        return true;
    }

    public static boolean addPlayerToGlobalChat(Player sender, Command command){
        if(!command.getName().equalsIgnoreCase("global")) return false ;
        ChatManagerService.addPlayerToGlobalChat(sender);
        sender.sendMessage(ChatColorUtil.textColor("Voc\u00EA entrou no Chat Global", ChatColor.GREEN));
        return  true;
    }

    public static boolean addPlayerToLocalChat(Player sender, Command command){
        if(!command.getName().equalsIgnoreCase("local")) return false ;
        ChatManagerService.removePlayerToGlobalChat(sender);
        sender.sendMessage(ChatColorUtil.textColor("Voc\u00EA entrou no Chat Local", ChatColor.GREEN));
        return  true;
    }

    public static boolean whisperToPlayer(Player sender, Command command, String...args) {
        if(!command.getName().equalsIgnoreCase("whisper") ) return false ;

        if(args == null || args.length < 2){
            sender.sendMessage(ChatColorUtil.textColor("Mensagem inv\u00E1lida tente: /w <Player> <Digite seu texto aqui>",ChatColor.RED));
            return  false;
        }

        final String[] message = Arrays.copyOfRange(args, 1, args.length);
        sender.sendMessage(ChatColorUtil.textColor(sender.getName() + " -> "+ args[0], ChatColor.GRAY) + ChatColorUtil.textColor(" >> ", ChatColor.GOLD) + ChatColorUtil.textColor(message, ChatColor.LIGHT_PURPLE));

        Bukkit.getServer().getOnlinePlayers().forEach(receiver -> {
           if( ChatManagerService.playerHasOpOrPermission(receiver) && !sender.getName().equalsIgnoreCase(receiver.getName())){
               receiver.sendMessage( ChatColorUtil.textColor(sender.getName()+" -> "+ args[0] + " >> ", ChatColor.AQUA) + ChatColorUtil.textColor(message,ChatColor.GRAY));
               return;
           }

           if(receiver.getName().equalsIgnoreCase(args[0]) && !sender.getName().equalsIgnoreCase(receiver.getName())){
               receiver.sendMessage(ChatColorUtil.textColor(sender.getName() + " sussurrou ", ChatColor.GRAY) + ChatColorUtil.textColor(" >> ", ChatColor.AQUA) + ChatColorUtil.textColor(message, ChatColor.LIGHT_PURPLE));
           }
        });

        return true;
    }

    public static boolean replyToPlayer(Player replier, Command command, String...args) {
        if(!command.getName().equalsIgnoreCase("reply") ) return false ;

        if(args == null){
            replier.sendMessage(ChatColorUtil.textColor("Mensagem inv\u00E1lida tente: /r <Digite seu texto aqui>",ChatColor.RED));
            return  false;
        }
        Player lastPlayer =  ChatManagerService.getLastPlayerToReply(replier);

        if(lastPlayer == null){
            replier.sendMessage(ChatColorUtil.textColor("Nenhum player lhe enviou mensagem ainda",ChatColor.RED));
            return  false;
        }

        replier.sendMessage(ChatColorUtil.textColor(replier.getName() + " -> "+lastPlayer.getName() , ChatColor.GRAY) + ChatColorUtil.textColor(" >> ", ChatColor.GOLD) + ChatColorUtil.textColor(args, ChatColor.LIGHT_PURPLE));

        Bukkit.getServer().getOnlinePlayers().forEach(receiver -> {
            if( ChatManagerService.playerHasOpOrPermission(receiver) && !replier.getName().equalsIgnoreCase(receiver.getName())){
                receiver.sendMessage( ChatColorUtil.textColor(replier.getName()+" -> "+ lastPlayer.getName() + " >> ", ChatColor.AQUA) + ChatColorUtil.textColor(args,ChatColor.GRAY));
                return;
            }

            if(receiver.getName().equalsIgnoreCase(lastPlayer.getName()) && !replier.getName().equalsIgnoreCase(receiver.getName())){
                receiver.sendMessage(ChatColorUtil.textColor(replier.getName() + " sussurrou ", ChatColor.GRAY) + ChatColorUtil.textColor(" >> ", ChatColor.AQUA) + ChatColorUtil.textColor(args, ChatColor.LIGHT_PURPLE));
                ChatManagerService.addLastPlayerToReplay(replier, receiver);
            }
        });

        return true;
    }
}
