package org.lzmhc.panel;

import org.lzmhc.panel.component.GetPanel;

import javax.swing.*;
import java.awt.*;

public class templateTab extends JPanel{
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel dataPanel;
    public templateTab(GetPanel panel){
        this.setLayout(new GridLayout(1,2));
        this.leftPanel=new JPanel(new GridLayout(2,1));
        this.rightPanel=new JPanel();
        this.dataPanel=panel.getPanel();
        this.leftPanel.add(dataPanel);
        this.add(leftPanel);
        this.add(rightPanel);
    }
}
