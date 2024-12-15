package org.lzmhc.panel.tab;

import org.lzmhc.handle.GraphicsCardHandle;
import org.lzmhc.handle.ProcessorHandle;
import org.lzmhc.panel.component.GraphicsPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class GraphicsTab extends GraphicsPanel implements templateTab {
    private ImageIcon icon;
    public GraphicsTab(String path) {
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
//        Timer time = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
                Thread thread = new GraphicsCardHandle(graphicsCardDto, graphicsCards,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                panel.removeAll();
                panel.add(new tabItem("型号",graphicsCardDto.getName()));
                panel.add(new tabItem("供应商", graphicsCardDto.getVendor()));
                panel.add(new tabItem("显存", graphicsCardDto.getVram()));
                updateUI();
//            }
//        });
//        time.start();
        return panel;
    }
}
