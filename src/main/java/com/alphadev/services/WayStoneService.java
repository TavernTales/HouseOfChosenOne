package com.alphadev.services;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.entity.CustomLocation;
import com.alphadev.entity.WayStone;
import com.alphadev.enums.WayStoneEnum;
import com.alphadev.utils.ChatColorUtil;
import com.alphadev.utils.HelpUtils;
import com.alphadev.utils.ItemFactoryUtil;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class WayStoneService {

    private static HashMap<UUID, WayStone> personalWaystoneMap = new HashMap<>();

    public static void openWayStoneGUIByName(Player player, WayStoneEnum wayStoneEnum){
        Inventory inventory = Bukkit.createInventory(player,54, ChatColorUtil.textColor(wayStoneEnum.getTitle(), ChatColor.WHITE));
        inventory.setItem(18, ItemFactoryUtil.invisibleBack());
        inventory.setItem(27, ItemFactoryUtil.invisibleBack());

        inventory.setItem(26, ItemFactoryUtil.invisibleNext());
        inventory.setItem(35, ItemFactoryUtil.invisibleNext());


        inventory.setItem(48, ItemFactoryUtil.invisibleConfirm());
        inventory.setItem(49, ItemFactoryUtil.invisibleConfirm());
        inventory.setItem(50, ItemFactoryUtil.invisibleConfirm());

        player.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN,0.2F ,0.2F);
        player.openInventory(inventory);
    }

    public static void toggleWayStoneOption(InventoryClickEvent event){

        if(WayStoneEnum.fromTitle(ChatColor.stripColor(event.getView().getTitle())).equals(WayStoneEnum.UNKNOW))
            return;

        WayStoneEnum wayStoneEnum = WayStoneEnum.fromTitle(ChatColor.stripColor(event.getView().getTitle()));
        event.setCancelled(true);

        if(Objects.equals(event.getCurrentItem(), ItemFactoryUtil.invisibleBack()))
            switch (wayStoneEnum){
                case JAIL -> openWayStoneGUIByName((Player) event.getWhoClicked(), WayStoneEnum.BROTHERLAND);
                case BROTHERLAND -> openWayStoneGUIByName((Player) event.getWhoClicked(), WayStoneEnum.NETHERLAND);
            }

        if(Objects.equals(event.getCurrentItem(), ItemFactoryUtil.invisibleNext()))
            switch (wayStoneEnum){
                case NETHERLAND -> openWayStoneGUIByName((Player) event.getWhoClicked(), WayStoneEnum.BROTHERLAND);
                case BROTHERLAND -> openWayStoneGUIByName((Player) event.getWhoClicked(), WayStoneEnum.JAIL);
            }

        if(Objects.equals(event.getCurrentItem(), ItemFactoryUtil.invisibleConfirm())){
           Player player = (Player) event.getWhoClicked();

            player.closeInventory();
            player.playSound(player,Sound.BLOCK_BEACON_ACTIVATE , 0.2F, 0.2F);
            teleportPrepare(player, wayStoneEnum);
        }

    }

    static HashMap<UUID, Integer> playerTeleportScheduler = new HashMap<>();
    private static void teleportPrepare(Player player, WayStoneEnum wayStoneEnum){
        long startTime = System.currentTimeMillis();

        AtomicReference<Double> particlesVariation = new AtomicReference<>((double) 0);
        AtomicReference<Location> first = new AtomicReference<>(player.getLocation());
        AtomicReference<Location> second = new AtomicReference<>(player.getLocation());

        playerTeleportScheduler.put(player.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(HouseOfChosenOne.getPlugin(), () -> {

             if(HelpUtils.convertMillisToSeconds(startTime, System.currentTimeMillis()) < 2){

                 particlesVariation.updateAndGet(v -> v + Math.PI / 16);

                 Location currentLocation = player.getLocation();
                 currentLocation.setY(currentLocation.getY() + 0.1);

                 first.set(currentLocation.clone().add(Math.cos(particlesVariation.get()), Math.sin(particlesVariation.get()) + 1, Math.sin(particlesVariation.get())));
                 second.set(currentLocation.clone().add(Math.cos(particlesVariation.get() + Math.PI), Math.sin(particlesVariation.get()) + 1, Math.sin(particlesVariation.get()+ Math.PI)));

                 player.teleport(currentLocation);
                 player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, first.get(), 0);
                 player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, second.get(), 0);
                 player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, currentLocation, 0);
             }else {
                 player.sendTitle(wayStoneEnum.getName(),"", 5, 40 , 5);
                 player.teleport(Objects.requireNonNull(player.getBedSpawnLocation()));
                 Bukkit.getScheduler().cancelTask(playerTeleportScheduler.get(player.getUniqueId()));
             }

         }, 0,1));

    }




    public static void placePersonalWaystone(EntitySpawnEvent event){

        if(!event.getEntityType().equals(EntityType.ARMOR_STAND))
            return;

        /*Player player = eve;

        WayStone wayStone = new WayStone().setPlayerUUID(player.getUniqueId())
                .setLocale(new CustomLocation(event.getLocation()))
                .setSpawn(new CustomLocation(player.getLocation()))
                .setDestiny(new CustomLocation(player.getBedSpawnLocation()))
                .setWayStoneReferenceObject((ArmorStand) event.getBlock());


        player.sendMessage("Você criou uma WayStone privada");
        personalWaystoneMap.put(player.getUniqueId(), wayStone);*/
    }



    public  static void interactWithPersonalWayStone(PlayerInteractAtEntityEvent event){
        if(!event.getRightClicked().getType().equals(EntityType.ARMOR_STAND))
            return;

        if(personalWaystoneMap.get(event.getPlayer().getUniqueId()).getWayStoneReferenceObject().equals(event.getRightClicked()))
            event.getPlayer().sendMessage("Batata");

    }


}
