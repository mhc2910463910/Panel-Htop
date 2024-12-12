package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProcessorDto {
        private String name;
        /**
         * 名称
         */
        private int coreCount;
        /**
         * 可用于处理的逻辑CPU核心数
         */
        private String maxFreq;
        /**
         * 最大频率
         */
        private String currentFreq;
        /**
         * 当前频率
         */
        private String bitDepth;
        /**
         * 位
         */
        private String sensorsVoltage;
        /**
         * CPU电压
         */
        private String sensoresTemperature;
        /**
         * CPU温度
         */
        private List<String> sensoresSpeedList;
        /**
         * 风扇速度
         */
        private String usedRate;
        /**
         * 利用率
         */
}
