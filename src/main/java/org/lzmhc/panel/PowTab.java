package org.lzmhc.panel;

import org.lzmhc.panel.component.GetPanel;
import org.lzmhc.panel.component.PowPanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;

public class PowTab extends PowPanel implements templateTab {
    private ImageIcon icon;
    public PowTab(String icon) {
        this.icon= IconUtil.loadIcon(new ImageIcon(icon), 128);
        JLabel label=new JLabel(this.icon);
        this.add(label);
    }
}
