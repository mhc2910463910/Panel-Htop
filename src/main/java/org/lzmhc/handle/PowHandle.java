package org.lzmhc.handle;

import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.PowerDto;
import oshi.hardware.PowerSource;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class PowHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public PowHandle(InfoItem infoItem, Object obj, CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }
    @Override
    public void build() {
        PowerDto powerDto = (PowerDto) infoItem;
        List<PowerSource> powerSources = (List<PowerSource>) obj;
        for(PowerSource powerSource:powerSources){
            if(!powerSource.getDeviceName().equals("unknown")){
                powerDto.setName(powerSource.getName());
                powerDto.setDeviceName(powerSource.getDeviceName());
                DecimalFormat format=new DecimalFormat("#.00");
                String voltage = format.format((powerSource.getVoltage()));
                powerDto.setVoltage(voltage+" V");
                powerDto.setPowerOnLine(powerSource.isPowerOnLine());
                powerDto.setCharging(powerSource.isCharging());
                powerDto.setDischarging(powerSource.isDischarging());
                powerDto.setCurrentCapacity(powerSource.getCurrentCapacity());
                powerDto.setMaxCapacity(powerSource.getMaxCapacity());
                powerDto.setDesignCapacity(powerSource.getDesignCapacity());
                powerDto.setChemistry(powerSource.getChemistry());
                powerDto.setManufacturer(powerSource.getManufacturer());
                return;
            }
        }
    }

    @Override
    public void run() {
        try{
            this.build();
        }finally {
            latch.countDown();
        }
    }
}
