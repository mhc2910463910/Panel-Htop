package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;
import oshi.software.os.OperatingSystem;

@Setter
@Getter
public class OperatingSystemDto implements InfoItem {
    private String family;
    //    系统家族
    private int bitness;
    //    操作系统的位数
    private OperatingSystem.OSVersionInfo versionInfo;
    //    操作系统版本信息
    private String systemboottime;
    //    操作Unix启动时间
    private int processCount;
    /**
     * 进程数量
     */
    private String runTime;
    /**
     * 系统运行时间
     */
    private String currentTime;
    /**
     * 当前时间
     */
    private String manufacturer;
    /**
     * 操作系统制造商
     */
    private int processId;
    /**
     * 当前进程PID
     */
}