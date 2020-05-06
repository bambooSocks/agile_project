package rcm.ui.journey;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.xy.XYDataset;

import rcm.model.Application;
import rcm.model.ContainerStatus;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public class GraphPressure extends BaseGraph {

    /**
     * 
     */
    private static final long serialVersionUID = 7053624937489967264L;

    public GraphPressure(Application app, int id) {
        super(app, id);
    }


    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Atmospheric Pressure");

        List<ContainerStatus> statuses = app.requestStatus(id);
        for (ContainerStatus s : statuses) {
            LocalDateTime date = s.getTimestamp();
            int min = date.getMinute();
            int h = date.getHour();
            int d = date.getDayOfMonth();
            int m = date.getMonthValue();
            int y = date.getYear();
            double value = s.getPressure();
            series.add(new Minute(min, h, d, m, y), value);
        }

//        series.add(new Minute(50,22,1,1,2017), 56.9);
//        series.add(new Minute(50,22,2,1,2017), 58.9);
//        series.add(new Minute(50,22,3,1,2017), 60.9);
//        series.add(new Minute(0,8,3,1,2017), 58.9);

        dataset.addSeries(series);

        return dataset;
    }

    @Override
    public JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Atmospheric Pressure", "Date", "Pressure (atm)",
                createDataset());

        XYPlot plot = (XYPlot) chart.getPlot();

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm dd/MM/yy"));
        plot.setDomainAxis(dateAxis);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        renderer.setSeriesPaint(0, Color.BLUE);
        chart.setTitle(new TextTitle("Pressure of the container during the journey",
                new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }

}