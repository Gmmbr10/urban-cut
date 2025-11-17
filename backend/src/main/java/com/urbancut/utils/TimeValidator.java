package com.urbancut.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeValidator {
    public final static String regex = "(\\d\\d:\\d\\d)";
    public final static Pattern pattern = Pattern.compile(regex);

    public static boolean validar(String time){
        Matcher matcher = pattern.matcher(time);
        return matcher.find();
    }
}
