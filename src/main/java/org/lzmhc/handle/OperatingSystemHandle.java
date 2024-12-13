package org.lzmhc.handle;

import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.OperatingSystemDto;
import oshi.software.os.OperatingSystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class OperatingSystemHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public OperatingSystemHandle(InfoItem infoItem, Object obj, CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }
    @Override
    public void build() {
        OperatingSystemDto operatingSystemDto= (OperatingSystemDto) infoItem;
        OperatingSystem operatingSystem = (OperatingSystem) obj;
        operatingSystemDto.setBitness(operatingSystem.getBitness());
        operatingSystemDto.setFamily(operatingSystem.getFamily());
        operatingSystemDto.setVersionInfo(operatingSystem.getVersionInfo());
        Long timestamp = operatingSystem.getSystemBootTime();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp*1000));
        operatingSystemDto.setSystemboottime(date);
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
