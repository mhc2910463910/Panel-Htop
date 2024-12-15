package org.lzmhc.panel;

import org.lzmhc.panel.component.GetPanel;
import org.lzmhc.panel.component.OperatingSystemPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;

public class OperatingSystemTab extends OperatingSystemPanel implements templateTab {
    private ImageIcon icon;
    public OperatingSystemTab(String icon) {
        this.icon= IconUtil.loadIcon(new ImageIcon(icon), 128);
        JLabel label=new JLabel(this.icon);
        this.add(label);
    }
}
