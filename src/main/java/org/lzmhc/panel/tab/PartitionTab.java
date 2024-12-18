package org.lzmhc.panel.tab;

import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.panel.component.PartitionPanel;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PartitionTab extends JPanel implements templateTab{
    FileSystem fileSystem = InfoDtoSingleton.getInfoDto().getOperatingSystem().getFileSystem();
    public PartitionTab(){
        this.setLayout(new GridLayout(10,1));
        this.add(PartitionPanel.getTitleTable());
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        for(OSFileStore fileStore: fileStores){
            this.add(new PartitionPanel(fileStore.getName(), fileStore.getMount(), fileStore.getType(), fileStore.getFreeSpace(), fileStore.getUsableSpace(), fileStore.getTotalSpace()));
        }
    }
}
