package com.alphadev.utils;

import java.util.Collection;
import java.util.List;

public class HelpUtils {
    public static final List<String> HOUSES = List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris");

    public static double sortPercent(){
        return Math.random() * 100;
    }

    public static boolean isNullOrEmpty(Collection collections){
        return  collections == null || collections.isEmpty();
    }
}
