package org.linwea.smsforward;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkPhone {


    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
}
