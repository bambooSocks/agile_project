package rcm.ui;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.xy.XYDataset;
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

public class GraphHumidity extends BaseGraph {

    @Override
    public XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Humidity");

        series.add(new Day(1, 1, 2017), 56.9);
        series.add(new Day(2, 1, 2017), 58.9);
        series.add(new Day(3, 1, 2017), 57.9);
        series.add(new Day(4, 1, 2017), 57.9);
        series.add(new Day(5, 1, 2017), 57.3);
        series.add(new Day(6, 1, 2017), 58.8);
        series.add(new Day(7, 1, 2017), 57.8);

        dataset.addSeries(series);

        return dataset;
    }

    @Override
    public JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Humidity %", "Date", "Humidity",
                dataset);

        XYPlot plot = (XYPlot) chart.getPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        renderer.setSeriesPaint(0, Color.CYAN);
        chart.setTitle(new TextTitle("Humidity of the container during the journey",
                new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }

}