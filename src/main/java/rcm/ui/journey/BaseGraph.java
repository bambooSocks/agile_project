package rcm.ui.journey;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import rcm.model.Application;
import rcm.model.ContainerStatus;

public abstract class BaseGraph extends JPanel {

    private static final long serialVersionUID = 9059820726277444630L;
    protected Application app;
    protected int id;
    private ChartPanel chartPanel;

    public BaseGraph(Application app, int id) {
        this.app = app;
        this.id = id;
        initUI();

    }

    private void initUI() {
        chartPanel = new ChartPanel(null);
        getChartPanel().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getChartPanel().setBackground(new Color(220, 220, 220));
        add(getChartPanel());
    }

    public void updateGraph(int id) {
        this.id = id;
        JFreeChart chart = createChart();
        getChartPanel().setChart(chart);
    }

    public abstract JFreeChart createChart();

    public abstract List<ContainerStatus> getStatus(int journey_id);
    
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

}