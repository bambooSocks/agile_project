package rcm.ui.journey;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

import rcm.model.Application;

public abstract class BaseGraph extends JPanel {
    protected Application app;

    public BaseGraph() {
 
        initUI();

    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(new Color(220, 220, 220));
        chartPanel.setPreferredSize(new Dimension(650, 400));
        add(chartPanel);
    }

    public abstract XYDataset createDataset();

    public abstract JFreeChart createChart(XYDataset dataset);
}