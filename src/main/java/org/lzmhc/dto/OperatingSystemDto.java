package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import oshi.software.os.OperatingSystem;

@Setter
@Getter
public class OperatingSystemDto {
    private String family;
//    系统家族
    private int bitness;
//    操作系统的位数
    private OperatingSystem.OSVersionInfo versionInfo;
//    操作系统版本信息
    private String systemboottime;
//    操作Unix启动时间
}
