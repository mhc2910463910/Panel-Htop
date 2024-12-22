package org.lzmhc.panel.tab;

import lombok.extern.log4j.Log4j;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.logging.Logger;

public class AboutTab extends JPanel{
    public AboutTab(){
        JLabel title = new JLabel(IconUtil.loadIcon(new ImageIcon("img/logo.png"),256));
        JLabel label=new JLabel("使用OSHI模块和FlatLaf开发的一款可视化系统信息面板。");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel btnlabel=new JLabel(IconUtil.loadIcon(new ImageIcon("img/github-mark-white.png"),64),0);
        btnlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Desktop.isDesktopSupported()){
                    System.out.println("支持");
                    Desktop desktop=Desktop.getDesktop();
                    if(desktop.isSupported(Desktop.Action.BROWSE)){
                        try{
                            URI url=new URI("https://github.com/mhc2910463910/Panel-Htop");
                            desktop.browse(url);
                        }catch (Exception err){
                            err.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("不支持");
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