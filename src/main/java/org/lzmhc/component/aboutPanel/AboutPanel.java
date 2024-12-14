package org.lzmhc.component.aboutPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class AboutPanel extends JPanel{
    public AboutPanel(){
        JLabel label=new JLabel("此项目是一款功能类似htop命令的可视化系统信息面板。");
        JLabel label1 = new JLabel("This project is a visual system information panel with functions similar to the HTOP command.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("img/github-mark.png");
        Image image = icon.getImage();
        Image scaleImg = image.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        ImageIcon scaleImage = new ImageIcon(scaleImg);
        JLabel btnlabel=new JLabel( "release",scaleImage,0);
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
        LayoutManager Layout=new GridLayout(6,1);
        this.setLayout(Layout);
        this.add(label);
        this.add(label1);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(btnlabel);
    }
}