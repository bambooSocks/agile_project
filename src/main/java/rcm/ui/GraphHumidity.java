package rcm.ui; 
 
 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
 
import org.jfree.chart.ChartFactory; 
 
import org.jfree.chart.block.BlockBorder; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer; 
import org.jfree.chart.title.TextTitle; 
 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Font; 
 
 
public class GraphHumidity extends BaseGraph { 
 
    @Override 
    public XYDataset createDataset() { 
        XYSeries series = new XYSeries("Humidity"); 
        series.add(18, 567); 
        series.add(20, 612); 
        series.add(25, 800); 
        series.add(30, 980); 
        series.add(40, 1410); 
        series.add(50, 2350); 
 
        XYSeriesCollection dataset = new XYSeriesCollection(); 
        dataset.addSeries(series); 
 
        return dataset; 
    } 
 
    @Override 
    public JFreeChart createChart(XYDataset dataset) { 
        JFreeChart chart = ChartFactory.createXYLineChart("Himidity of the container during the journey", 
                "Time (dd/mm/yy hh:mm)", "Humidity (%)", dataset, PlotOrientation.VERTICAL, true, true, false); 
 
        XYPlot plot = chart.getXYPlot(); 
 
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
