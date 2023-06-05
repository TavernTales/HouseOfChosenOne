package com.alphadev.manager;

import com.alphadev.HOCOPlugin;
import com.alphadev.entities.HCOHouse;
import com.alphadev.utils.ChatColorUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class SignHouseManager {
    private static final String DIVISOR_STRING = "====================================";
    private static final String ZERONIA = "zeronia";
    private static final String VLAROLA = "vlarola";
    private static final String FRANDHRA = "frandhra";
    private static final String NASHOR = "nashor";
    private static final String DRAKKARIS = "drakkaris";

    private final List<HCOHouse> hcoHouses = HOCOPlugin.hcoHouseConstantInfo;

    public SignHouseManager() {}

    public static void signPlaceHouseSetup(SignChangeEvent signChangeEvent) {
        if (!signChangeEvent.getPlayer().hasPermission("hco.admin")) {
            return;
        }

        String firstLine = signChangeEvent.getLine(0).toLowerCase();

        switch (firstLine) {
            case ZERONIA -> configureSign(signChangeEvent, "Zeronia", ChatColor.RED);
            case VLAROLA -> configureSign(signChangeEvent, "Vlarola", ChatColor.BLUE);
            case FRANDHRA -> configureSign(signChangeEvent, "Frandhra", ChatColor.GREEN);
            case NASHOR -> configureSign(signChangeEvent, "Nashor", ChatColor.DARK_RED);
            case DRAKKARIS -> configureSign(signChangeEvent, "Drakkaris", ChatColor.DARK_GRAY);
            default -> {
            }
        }
    }

    private static void configureSign(SignChangeEvent signChangeEvent, String houseName, ChatColor houseColor) {
        signChangeEvent.setLine(0, ChatColorUtil.boldText(houseName, houseColor));
        signChangeEvent.setLine(1, ChatColorUtil.textColor("Informa\u00E7\u00E3o", ChatColor.WHITE));
        signChangeEvent.setLine(2, "");
        signChangeEvent.setLine(3, ChatColorUtil.boldText("[Clique]", ChatColor.WHITE));
    }

    public void signHouseInteract(PlayerInteractEvent playerInteractEvent) {
        if (!playerInteractEvent.hasBlock()) {
            return;
        }

        if (!(playerInteractEvent.getClickedBlock().getState() instanceof Sign clickedSign)) {
            return;
        }

        Player player = playerInteractEvent.getPlayer();
        String firstLine = ChatColor.stripColor(clickedSign.getLine(0)).toLowerCase();
        HCOHouse chosenHcoHouse;

        switch (firstLine) {
            case ZERONIA -> {
                chosenHcoHouse = hcoHouses.get(0);
                sendMessageHouseDetails(player, chosenHcoHouse, ChatColor.RED, ZERONIA);
            }
            case VLAROLA -> {
                chosenHcoHouse = hcoHouses.get(1);
                sendMessageHouseDetails(player, chosenHcoHouse, ChatColor.BLUE, VLAROLA);
            }
            case FRANDHRA -> {
                chosenHcoHouse = hcoHouses.get(2);
                sendMessageHouseDetails(player, chosenHcoHouse, ChatColor.DARK_GREEN, FRANDHRA);
            }
            case NASHOR -> {
                chosenHcoHouse = hcoHouses.get(3);
                sendMessageHouseDetails(player, chosenHcoHouse, ChatColor.DARK_RED, NASHOR);
            }
            case DRAKKARIS -> {
                chosenHcoHouse = hcoHouses.get(4);
                sendMessageHouseDetails(player, chosenHcoHouse, ChatColor.DARK_GRAY, DRAKKARIS);
            }
            default -> {
            }
        }
    }

    private static void sendMessageHouseDetails(Player player, HCOHouse chosenHcoHouse, ChatColor houseColor, String houseName) {
        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING, ChatColor.GREEN));
        player.sendMessage(ChatColorUtil.boldText(chosenHcoHouse.name(), houseColor));
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Detalhes: ") + ChatColor.RESET + chosenHcoHouse.description());
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText("Objetivos: ") + ChatColor.RESET + chosenHcoHouse.objective());
        player.sendMessage("");

        TextComponent message = new TextComponent("CLIQUE PARA ENTRAR NA CASA");
        message.setBold(true);
        message.setColor(net.md_5.bungee.api.ChatColor.AQUA);
        message.setUnderlined(true);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/houseofchosenone join " + houseName));

        player.spigot().sendMessage(message);
        player.sendMessage("");
        player.sendMessage(ChatColor.RED + " Aviso: ao selecionar a casa voc\u00EA ser\u00E1 teleportado para a capital, caso queira trocar de casa sofrer\u00E1 uma penalidade de 48Horas para selecionar uma nova casa e perder\u00E1 o seu progresso. '/hco leave'");
        player.sendMessage("");
        player.sendMessage(ChatColorUtil.boldText(DIVISOR_STRING, ChatColor.GREEN));
    }
}