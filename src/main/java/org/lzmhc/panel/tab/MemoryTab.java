package org.lzmhc.panel.tab;

import org.lzmhc.handle.MemoryHandle;
import org.lzmhc.panel.component.MemoryPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class MemoryTab extends MemoryPanel implements templateTab {
    private ImageIcon icon;
    public MemoryTab(String path) {
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
                Thread thread = new MemoryHandle(globalMemoryDto,globalMemory ,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                panel.removeAll();
                panel.add(new tabItem("使用率", globalMemoryDto.getPercentage()+"%"));
                panel.add(new tabItem("物理内存", globalMemoryDto.getTotalMemory()));
                panel.add(new tabItem("已使用", globalMemoryDto.getUsedMemory()));
                panel.add(new tabItem("可用", globalMemoryDto.getAvailableMemory()));
                panel.add(new tabItem("虚拟内存", globalMemoryDto.getVirtuallMemory()));
                panel.add(new tabItem("已使用", globalMemoryDto.getVirtualUsedMemory()));
                panel.add(new tabItem("可用", globalMemoryDto.getAvailabelVirtualMemory()));
                panel.add(new tabItem("内存类型 or bit",globalMemoryDto.getRamTypeOrOsBitDepth()));
                updateUI();
            }
        });
        time.start();
        return panel;
    }
}
