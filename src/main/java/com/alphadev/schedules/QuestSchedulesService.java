package com.alphadev.schedules;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.utils.config.ConfigQuests;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.time.LocalDateTime;

public class QuestSchedulesService {
    private final ConfigurationSection configQuests = new ConfigQuests().getQuestsConfigurationSettings();
    private final Integer queueTimeInMinutes =  configQuests.contains("queue-time-in-minutes") ? configQuests.getInt("queue-time-in-minutes")*24*60 : 10*24*60;

    public void questScheduleDaily(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HouseOfChosenOne.getPlugin(), () -> {
            Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(LocalDateTime.now().toString()));
        },0,queueTimeInMinutes);
    }

}
