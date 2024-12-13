package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;
import oshi.hardware.GraphicsCard;

import java.util.List;

@Getter
@Setter
public class GraphicsCardDto implements InfoItem {
    private List<GraphicsCard> graphicsCardList;
    /**
     * 显卡
     */
}