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
        operatingSystemDto.setProcessCount(operatingSystem.getProcessCount());
        long runMillos = System.currentTimeMillis()- timestamp*1000;
        String runTime = new SimpleDateFormat("HH小时mm分钟ss秒").format(new Date(runMillos));
        operatingSystemDto.setRunTime(runTime+"");
        String currentTime=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date(System.currentTimeMillis()));
        operatingSystemDto.setCurrentTime(currentTime);
        operatingSystemDto.setManufacturer(operatingSystem.getManufacturer());
//        operatingSystemDto.setProcessId(operatingSystem.getProcessId());
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
