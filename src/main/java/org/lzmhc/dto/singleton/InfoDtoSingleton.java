package org.lzmhc.dto.singleton;

import oshi.SystemInfo;

/**
 * 单例模式
 */
public class InfoDtoSingleton {
    private static final SystemInfo systeminfo = new SystemInfo();
    public static SystemInfo getInfoDto(){
        return systeminfo;
    }
}
