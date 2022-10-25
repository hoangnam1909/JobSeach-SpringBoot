package com.nhn.util;

import java.util.regex.Pattern;

public class Util {

    public static boolean isBCrypt(String encodedPassword){
        Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
        return BCRYPT_PATTERN.matcher(encodedPassword).matches();
    }

}
