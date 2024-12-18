package org.lzmhc.handle;

import org.lzmhc.dto.GraphicsCardDto;
import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import oshi.hardware.GraphicsCard;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GraphicsCardHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public GraphicsCardHandle(InfoItem infoItem, Object obj, CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }
    @Override
    public void build() {
        GraphicsCardDto graphicsCardDto= (GraphicsCardDto) infoItem;
        List<GraphicsCard> graphicsCards = (List<GraphicsCard>) obj;
        graphicsCardDto.setName(graphicsCards.get(0).getName());
        graphicsCardDto.setVendor(graphicsCards.get(0).getVendor());
        graphicsCardDto.setDeviceId(graphicsCards.get(0).getDeviceId());
        graphicsCardDto.setVendor(graphicsCards.get(0).getVersionInfo());
        graphicsCardDto.setVram(graphicsCards.get(0).getVRam()/1024.0/1024.0 + "MB");
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
