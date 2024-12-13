package org.lzmhc.component;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
@Getter
@Setter
public class PanelItem {
    private String title;
    private InfoItem infoItem;
    private ArrayList<JLabel> labels;
    public PanelItem(String title){
        this.title=title;
        this.labels = new ArrayList<>();
    }
    public void addLabel(String name, String value){
        JLabel label= new JLabel(name+" : "+value);
        labels.add(label);
    }

}
