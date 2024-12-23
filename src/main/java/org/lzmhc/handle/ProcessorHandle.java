package org.lzmhc.handle;

import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.ProcessorDto;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import oshi.hardware.CentralProcessor;
import oshi.hardware.Sensors;
import oshi.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ProcessorHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public ProcessorHandle(InfoItem infoItem, Object obj,CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }
    private String getConvertedFrequency(long[] hertzArray){
        long totalFrequency = Arrays.stream(hertzArray).sum();
        long hertz = totalFrequency / hertzArray.length;
        if((hertz/1E+6)>999){
            return (Math.round((hertz / 1E+9)*10.0)/10.0)+" GHz";
        }else{
            return Math.round(hertz/1E+6)+" MHz";
        }
    }
    private String getConvertedFrequency(long hertz){
        if((hertz/1E+6)>999){
            return (Math.round((hertz / 1E+9)*10.0)/10.0)+" GHz";
        }else{
            return Math.round(hertz/1E+6)+" MHz";
        }
    }
    public void build() {
        ProcessorDto processorDto = (ProcessorDto) infoItem;
        CentralProcessor centralProcessor = (CentralProcessor)obj;
            processorDto.setBitDepth(centralProcessor.getProcessorIdentifier().isCpu64bit()?"64":"32"+"-bit");
            processorDto.setName(centralProcessor.getProcessorIdentifier().getName());
            processorDto.setCoreCount(centralProcessor.getLogicalProcessorCount());
            processorDto.setMaxFreq(getConvertedFrequency(centralProcessor.getMaxFreq()));
            processorDto.setCurrentFreq(getConvertedFrequency(centralProcessor.getCurrentFreq()));
        Sensors sensors = InfoDtoSingleton.getInfoDto().getHardware().getSensors();
            processorDto.setSensoresTemperature(String.format("%.2f", sensors.getCpuTemperature())+" ℃");
            processorDto.setSensorsVoltage(String.format("%.2f", sensors.getCpuVoltage())+" v");
        int[] fanSpeeds = sensors.getFanSpeeds();
        List<String> speedList = new ArrayList<>();
        for( int speed : fanSpeeds){
            speedList.add(String.valueOf(speed)+"rpm");
        }
            processorDto.setSensoresSpeedList(speedList);
        /**
         * 利用率计算
         */
        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.ordinal()];
        Util.sleep(1000);
        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.ordinal()];
        processorDto.setUsedRate(String.valueOf((int)Math.round((1-((double)(currIdleTicks-prevIdleTicks))/((double)(currTotalTicks-prevTotalTicks)))*100)));
        /**
         * 逻辑处理器
         */
        processorDto.setLogicalProcessors(centralProcessor.getLogicalProcessors());
        int num =0;
        for(CentralProcessor.LogicalProcessor logicalProcessor: centralProcessor.getLogicalProcessors()){
            num = (logicalProcessor.getPhysicalProcessorNumber()>num)? logicalProcessor.getPhysicalProcessorNumber():num;
        }
        num+=1;
        processorDto.setLogicalCoreNum(num);
    }

    @Override
    public void run() {
        try {
            this.build();
        }finally {
            latch.countDown();
        }
    }
}
