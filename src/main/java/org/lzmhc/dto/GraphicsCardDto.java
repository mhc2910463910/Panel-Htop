package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import oshi.hardware.GraphicsCard;

import java.util.List;

@Getter
@Setter
public class GraphicsCardDto {
    private List<GraphicsCard> graphicsCardList;
    /**
     * 显卡
     */
}
