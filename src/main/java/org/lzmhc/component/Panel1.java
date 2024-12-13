package org.lzmhc.component;

import com.alibaba.fastjson.JSONArray;
import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.ProcessorDto;
import org.lzmhc.dto.factory.DtoCreator;
import org.lzmhc.dto.factory.InfoDto;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.ProcessorHandle;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel1 extends JPanel{
    private SystemInfo systemInfo = InfoDtoSingleton.getInfoDto();
    private DtoCreator dtoCreator = new InfoDto();
    /**
     * 概览
     * @return
     */
    public Panel1(){
        GridLayout gridLayout = new GridLayout(2,3);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        setLayout(gridLayout);
        this.add(this.processPanel());
        this.add(this.processPane2());
        this.add(this.processPane3());
        this.add(this.processPane4());
        this.add(this.processPane5());
        this.add(this.processPane6());
    }
    /**
     * 处理器
     */
    private JPanel processPanel(){
        JPanel panel = new JPanel(new GridLayout(6,1));
        PanelItem item=new PanelItem("处理器");
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        ProcessorDto processorDto = dtoCreator.createDto(ProcessorDto.class);
        ProcessorHandle.build(processorDto, processor);
        item.setInfoItem(processorDto);
        item.addLabel("利用率",((ProcessorDto)item.getInfoItem()).getUsedRate()+"%");
        item.addLabel("CPU电压",((ProcessorDto)item.getInfoItem()).getSensorsVoltage());
        item.addLabel("CPU温度",(((ProcessorDto) item.getInfoItem()).getSensoresTemperature()));
        item.addLabel("处理器",(((ProcessorDto) item.getInfoItem()).getName()));
        item.addLabel("频率",(((ProcessorDto) item.getInfoItem()).getCurrentFreq()+"/"+((ProcessorDto) item.getInfoItem()).getMaxFreq()));
        item.addLabel("风扇速度",(((ProcessorDto) item.getInfoItem()).getSensoresSpeedList().get(0)));
        ArrayList<JLabel> labels = item.getLabels();
        for(JLabel label:labels){
            panel.add(label);
        }
        return panel;
    }

    /**
     * 内存
     * @return
     */
    private JPanel processPane2(){
        JPanel panel = new JPanel(new GridLayout(6,1));
        JLabel title = new JLabel("内存");
        JLabel label1 = new JLabel("使用率：45% ");
        JLabel label2 = new JLabel("已使用：4.7G/15.5G");
        JLabel label3 = new JLabel("可用：6.7G");
        JLabel label4 = new JLabel("虚拟内存：8.00G");
        JLabel label5 = new JLabel("内存类型：DDR3");
        panel.setSize(425,540);
        panel.setBackground(Color.GRAY);
        panel.add(title);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        return panel;
    }
    private JPanel processPane3(){
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel("磁盘");
        JLabel label1 = new JLabel(" 45% ");
        JLabel label2 = new JLabel("磁盘类型：标准磁盘");
        JLabel label3 = new JLabel("磁盘容量：467GB");
        panel.setSize(425,540);
        panel.setBackground(Color.GRAY);
        panel.add(title);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        return panel;
    }
    private JPanel processPane4(){
        JPanel panel = new JPanel(new GridLayout(5,1));
        JLabel title = new JLabel(" 操作系统 ");
        JLabel label1 = new JLabel(" deepin ");
        JLabel label2 = new JLabel(" 版本：10.0");
        JLabel label3 = new JLabel(" 操作系统版本号：20.9");
        JLabel label4 = new JLabel(" 操作系统位：64");
        panel.setSize(425,540);
        panel.setBackground(Color.GRAY);
        panel.add(title);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        return panel;
    }
    private JPanel processPane5(){
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(" 显卡 ");
        JLabel label1 = new JLabel("型号：AMD Radeon R4 Graphics");
        JLabel label2 = new JLabel("供应商：Advanced Micro Devices");
        JLabel label3 = new JLabel("显存：1024MB");
        panel.setSize(425,540);
        panel.setBackground(Color.GRAY);
        panel.add(title);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        return panel;
    }
    private JPanel processPane6(){
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(" 电源 ");
        JLabel label1 = new JLabel(" 名称：X555-50");
        JLabel label2 = new JLabel(" 电压：8.0v");
        JLabel label3 = new JLabel(" 健康度：99%");
        panel.setSize(425,540);
        panel.setBackground(Color.GRAY);
        panel.add(title);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        return panel;
    }
}
