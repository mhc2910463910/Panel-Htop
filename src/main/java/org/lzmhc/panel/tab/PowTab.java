package org.lzmhc.panel.tab;

import org.lzmhc.handle.PowHandle;
import org.lzmhc.handle.ProcessorHandle;
import org.lzmhc.panel.component.PowPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class PowTab extends PowPanel implements templateTab {
    private ImageIcon icon;
    public PowTab(String path) {
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
    public JPanel getPanel() {
        JPanel panel=new JPanel();
        LayoutManager layoutManager=new GridLayout(12,1);
        panel.setLayout(layoutManager);
        CountDownLatch latch = new CountDownLatch(numThreads);
        Timer time = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new PowHandle(powerDto, powerSources,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                panel.removeAll();
                panel.add(new tabItem("电源名称",powerDto.getName()));
                panel.add(new tabItem("设备名称",powerDto.getDeviceName()));
                panel.add(new tabItem("电压", powerDto.getVoltage()));
                panel.add(new tabItem("当前电量", String.format("%.1f",powerDto.getCurrentCapacity()/powerDto.getMaxCapacity()*100)+"%"));
                panel.add(new tabItem("电池健康度", String.format("%.1f",powerDto.getMaxCapacity()/powerDto.getDesignCapacity()*100)+"%"));
                panel.add(new tabItem("充电中", powerDto.isCharging()?"是":"否"));
                panel.add(new tabItem("放电中", powerDto.isDischarging()?"是":"否"));
                panel.add(new tabItem("接入电源", powerDto.isPowerOnLine()?"是":"否"));
                panel.add(new tabItem("电池性质", powerDto.getChemistry()));
                panel.add(new tabItem("制造商", powerDto.getManufacturer()));
                updateUI();
            }
        });
        time.start();
        return panel;
    }
}
