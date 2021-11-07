package com.zj.util;

import java.util.UUID;

/**
 * @author zhoujian
 */
public class MyUUID {
    /**
     * 去除uuid中的 -
     *
     * @return uuid toString
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }
}

