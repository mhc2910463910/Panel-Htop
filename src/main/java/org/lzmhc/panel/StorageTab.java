package org.lzmhc.panel;

import org.lzmhc.panel.component.GetPanel;
import org.lzmhc.panel.component.StoragePanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;

public class StorageTab extends StoragePanel implements templateTab {
    private ImageIcon icon;
    public StorageTab(String icon) {
        this.icon= IconUtil.loadIcon(new ImageIcon(icon), 128);
        JLabel label=new JLabel(this.icon);
        this.add(label);
    }
}
