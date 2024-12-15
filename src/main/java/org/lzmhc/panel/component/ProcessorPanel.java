package org.lzmhc.panel.component;

import org.lzmhc.dto.ProcessorDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.ProcessorHandle;
import oshi.hardware.CentralProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class ProcessorPanel extends JPanel implements GetPanel{
    private CentralProcessor processor = InfoDtoSingleton.getInfoDto().getHardware().getProcessor();
    private ProcessorDto processorDto = InfoFactory.createDto(ProcessorDto.class);
//    public ProcessorPanel(){
//        this.getPanel();
//    }
    public JPanel getPanel(){
        ImageIcon icon = new ImageIcon("img/cpu.png");
        PanelItem item=new PanelItem("处理器",new GridLayout(7,1), icon);
        //item.putClientProperty("JComponent.background",Color.decode("#53354a"));
        CountDownLatch latch = new CountDownLatch(numThreads);
        Thread thread = new ProcessorHandle(processorDto, processor,latch);
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        item.addLabel("利用率", processorDto.getUsedRate() + "%");
        item.addLabel("CPU电压", processorDto.getSensorsVoltage());
        item.addLabel("CPU温度", processorDto.getSensoresTemperature());
        item.addLabel("处理器", processorDto.getName());
        item.addLabel("频率", processorDto.getCurrentFreq() + "/" + processorDto.getMaxFreq());
        item.addLabel("风扇速度", (processorDto.getSensoresSpeedList().size()>0)?processorDto.getSensoresSpeedList().get(0):"0");
        Timer time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new ProcessorHandle(processorDto, processor,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                item.removeAll();
                item.addLabel("利用率", processorDto.getUsedRate() + "%");
                item.addLabel("CPU电压", processorDto.getSensorsVoltage());
                item.addLabel("CPU温度", processorDto.getSensoresTemperature());
                item.addLabel("处理器", processorDto.getName());
                item.addLabel("频率", processorDto.getCurrentFreq() + "/" + processorDto.getMaxFreq());
                item.addLabel("风扇速度", (processorDto.getSensoresSpeedList().size()>0)?processorDto.getSensoresSpeedList().get(0):"0");
                item.updateUI();
            }
        });
        time.start();
        return item;
    }
}
