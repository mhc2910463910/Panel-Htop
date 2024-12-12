package org.lzmhc;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

/**
 * Hello world!
 */
public class HtopPanel extends JFrame {
    public HtopPanel(){
        initPanel();
    }
    public void initPanel(){
        setTitle("简单的监控面板");
        setSize(1080,720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            HtopPanel htopPanel=new HtopPanel();
            htopPanel.setVisible(true);
        });
    }
}
