package com.alphadev.schedules;

import com.alphadev.HouseOfChosenOne;
import com.alphadev.services.QuestService;
import com.alphadev.utils.config.ConfigQuests;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class QuestSchedulesService {
    private final ConfigurationSection configQuests = new ConfigQuests().getConfigurationSettings();
    private final Integer queueTimeInMinutes =  configQuests.contains("queue-time-in-minutes") ? configQuests.getInt("queue-time-in-minutes")*24*60 : 10*24*60;

//    public void questScheduleDaily(){
//        new BukkitRunnable() {
//            public void run() {
//                QuestService.getIstance().generateDayliQuests();
//            }
//        }.runTaskTimerAsynchronously(HouseOfChosenOne.getPlugin(),0,queueTimeInMinutes );
//    }

}
