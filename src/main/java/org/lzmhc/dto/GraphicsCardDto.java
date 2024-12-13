package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;
import oshi.hardware.GraphicsCard;

import java.util.List;

@Getter
@Setter
public class GraphicsCardDto implements InfoItem {
    private String name;
    /**
     * 显卡
     */
    private String vendor;
    /**
     * 供应商
     */
    private String vram;
    /**
     * 显存
     */
}
