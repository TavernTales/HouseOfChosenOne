package com.alphadev.events;

import com.alphadev.services.SignHouseService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeEventListener implements Listener {

    @EventHandler
    public void signPlaceEvent(SignChangeEvent event){
        SignHouseService.signPlaceHouseSetup(event);
    }
}
