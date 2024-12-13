package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

@Getter
@Setter
public class PowerDto implements InfoItem {
    /**
     * 操作系统级别的电源名称
     */
    private String name;
    /**
     * 设备级别的电源名称
     */
    private String deviceName;
    /**
     * 电压
     */
    private String voltage;
    /**
     * 是否接入电源
     */
    private boolean powerOnLine;
    /**
     * 是否充电
     */
    private boolean charging;
    /**
     * 是否在放电
     */
    private boolean discharging;
    /**
     * 当前容量
     */
    private double currentCapacity;
    /**
     * 最大容量
     */
    private double maxCapacity;
    /**
     * 设计容量
     */
    private double designCapacity;
    /**
     * 电池化学性质
     */
    private String chemistry;
    /**
     * 制造商
     */
    private String manufacturer;
}
