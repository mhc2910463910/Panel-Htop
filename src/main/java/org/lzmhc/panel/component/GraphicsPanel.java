package org.lzmhc.panel.component;

import org.lzmhc.dto.GraphicsCardDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.GraphicsCardHandle;
import oshi.hardware.GraphicsCard;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class GraphicsPanel extends JPanel implements GetPanel{
    protected java.util.List<GraphicsCard> graphicsCards = InfoDtoSingleton.getInfoDto().getHardware().getGraphicsCards();
    protected GraphicsCardDto graphicsCardDto = InfoFactory.createDto(GraphicsCardDto.class);
    @Override
    public JPanel getPanel(){
        ImageIcon icon = new ImageIcon("img/graphics.png");
        PanelItem item = new PanelItem(" 显卡 ",new GridLayout(4,1), icon);

        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread=new GraphicsCardHandle(graphicsCardDto, graphicsCards, latch);
        thread.start();
        try{
            latch.await();
        }catch (InterruptedException err){
            err.printStackTrace();
        }
        item.addLabel("型号", graphicsCardDto.getName());
        item.addLabel("供应商", graphicsCardDto.getVendor());
        item.addLabel("显存", graphicsCardDto.getVram());
        return item;
    }
}
