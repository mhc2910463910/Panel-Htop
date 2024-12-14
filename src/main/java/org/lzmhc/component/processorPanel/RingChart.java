package org.lzmhc.component.processorPanel;

import javax.swing.*;
import java.awt.*;

public class RingChart extends JPanel {
    private int[] data;  // 数据
    private String[] labels;  // 标签
    private Color[] colors;  // 颜色

    public RingChart(int[] data, String[] labels, Color[] colors) {
        this.data = data;
        this.labels = labels;
        this.colors = colors;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int total = 0;
        for (int i : data) {
            total += i;
        }

        int startAngle = 0;
        for (int i = 0; i < data.length; i++) {
            int angle = (int) Math.round(360.0 * data[i] / total);
            g2d.setColor(colors[i]);
            g2d.fillArc(50, 50, 200, 200, startAngle, angle);
            startAngle += angle;
        }

        // 绘制圆环边框
        g2d.setColor(Color.BLACK);
        g2d.drawOval(50, 50, 200, 200);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(75, 75, 150, 150);
    }

    public static void main(String[] args) {
        int[] data = {30, 20, 25, 15, 10};
        String[] labels = {"A", "B", "C", "D", "E"};
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};

        JFrame frame = new JFrame("Ring Chart Example");
        RingChart ringChart = new RingChart(data, labels, colors);
        frame.add(ringChart);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}