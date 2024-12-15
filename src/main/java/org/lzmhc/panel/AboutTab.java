package org.lzmhc.panel;

import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class AboutTab extends JPanel{
    public AboutTab(){
        JLabel title = new JLabel(IconUtil.loadIcon(new ImageIcon("img/cpu.png"),256));
        JLabel label=new JLabel("此项目是一款功能类似htop命令的可视化系统信息面板。");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("img/github-mark.png");
        JLabel btnlabel=new JLabel(IconUtil.loadIcon(new ImageIcon("img/github-mark.png"),64),0);
        btnlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Desktop.isDesktopSupported()){
                    Desktop desktop=Desktop.getDesktop();
                    if(desktop.isSupported(Desktop.Action.BROWSE)){
                        try{
                            URI url=new URI("https://github.com/mhc2910463910/Panel-Htop");
                            desktop.browse(url);
                        }catch (Exception err){
                            err.printStackTrace();
                        }
                    }
                }
            }
        });
        LayoutManager Layout=new GridLayout(3,1);
        this.setLayout(Layout);
        this.add(title);
        this.add(label);
        this.add(btnlabel);
    }
}