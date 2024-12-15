package org.lzmhc.panel.tab;

import org.lzmhc.handle.ProcessorHandle;
import org.lzmhc.panel.component.ProcessorPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class ProcessorTab extends ProcessorPanel implements templateTab {
    private ImageIcon icon;
    public ProcessorTab(String path){
        JPanel iconPanel=new JPanel();
        this.icon = IconUtil.loadIcon(new ImageIcon(path), 128);
        JLabel label = new JLabel( this.icon);
        iconPanel.add(label);
        iconPanel.setBackground(Color.decode("#155263"));
        this.setLayout(new BorderLayout());
        this.add(iconPanel, BorderLayout.NORTH);
        this.add(getPanel(), BorderLayout.CENTER);
    }
    @Override
    public JPanel getPanel(){
        JPanel panel=new JPanel();
        LayoutManager layoutManager=new GridLayout(12,1);
        panel.setLayout(layoutManager);
        CountDownLatch latch = new CountDownLatch(numThreads);
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
                panel.removeAll();
                panel.add(new tabItem("利用率",processorDto.getUsedRate() + "%"));
                panel.add(new tabItem("CPU电压", processorDto.getSensorsVoltage()));
                panel.add(new tabItem("CPU温度", processorDto.getSensoresTemperature()));
                panel.add(new tabItem("处理器", processorDto.getName()));
                panel.add(new tabItem("当前频率", processorDto.getCurrentFreq()));
                panel.add(new tabItem("最大频率", processorDto.getMaxFreq()));
                panel.add(new tabItem("风扇速度", ((processorDto.getSensoresSpeedList().size()>0) ? processorDto.getSensoresSpeedList().get(0) : "0")));
                updateUI();
            }
        });
        time.start();
        return panel;
    }
}