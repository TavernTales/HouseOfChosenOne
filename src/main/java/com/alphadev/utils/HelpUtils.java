package com.alphadev.utils;

import java.util.Collection;

public class HelpUtils {

    public static double sortPercent(){
        return Math.random() * 100;
    }

    public static boolean isNullOrEmpty(Collection collections){
        return  collections == null || collections.isEmpty();
    }
}
