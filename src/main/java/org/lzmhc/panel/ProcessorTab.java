package org.lzmhc.panel;

import org.lzmhc.panel.component.GetPanel;
import org.lzmhc.panel.component.ProcessorPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;

public class ProcessorTab extends ProcessorPanel implements templateTab {
    private ImageIcon icon;
    public ProcessorTab(String path){
        this.icon = IconUtil.loadIcon(new ImageIcon(path), 128);
        JLabel label = new JLabel( this.icon);
        this.add(label);
    }
}