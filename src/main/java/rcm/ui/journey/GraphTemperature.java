package rcm.ui.journey;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public abstract class GraphTemperature extends BaseGraph {

    private static final long serialVersionUID = -5061875398113958353L;

    public GraphTemperature(Application app, int id) {
        super(app, id);
    }

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Temperature in Celsius");

        List<ContainerStatus> statuses = getStatus(journey_id);

        if (statuses != null) {
            for (ContainerStatus s : statuses) {
                LocalDateTime date = s.getTimestamp();
                int min = date.getMinute();
                int h = date.getHour();
                int d = date.getDayOfMonth();
                int m = date.getMonthValue();
                int y = date.getYear();
                double value = s.getTemperature();
                series.addOrUpdate(new Minute(min, h, d, m, y), value);
            }
            setVisible(true);
        } else {
            setVisible(false);
        }
        dataset.addSeries(series);

        return dataset;
    }

    @Override
    public JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Temperature", "Date", "Temperature (Celsius)",
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

        renderer.setSeriesPaint(0, Color.RED);
        chart.setTitle(new TextTitle("Temperature of the container during the journey",
                new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }
}
