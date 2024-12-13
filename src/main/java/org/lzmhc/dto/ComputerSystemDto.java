package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

@Getter
@Setter
public class ComputerSystemDto implements InfoItem {
    private String manufacturer;
    /**
     * BIOS制造商
     */
    private String model;
    /**
     * 产品名称
     */
    private String version;
    /**
     * 版本
     */
    private String release_date;
    /**
     * 发布日期
     */
}
