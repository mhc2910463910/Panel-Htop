package org.lzmhc.panel.tab;

import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.panel.component.NetworkPanel;
import org.lzmhc.panel.component.PartitionPanel;
import oshi.hardware.NetworkIF;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NetworkTab extends JPanel implements templateTab{
    List<NetworkIF> networkIFs = InfoDtoSingleton.getInfoDto().getHardware().getNetworkIFs();
    public NetworkTab(){
        this.setLayout(new GridLayout(10,1));
        this.add(NetworkPanel.getTitleTable());
        for(NetworkIF networkIF: networkIFs){
            this.add(new NetworkPanel(networkIF.getName(), networkIF.getDisplayName(), networkIF.getIPv4addr(), networkIF.getIPv6addr(), networkIF.getMacaddr(), networkIF.getMTU()));
        }
    }
}
