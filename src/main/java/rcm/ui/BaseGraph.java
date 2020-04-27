package rcm.ui; 
 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
 
import javax.swing.BorderFactory; 
import javax.swing.JPanel; 
 
import java.awt.Color; 
import java.awt.Dimension; 
 
public abstract class BaseGraph extends JPanel { 
 
    public BaseGraph() { 
 
        initUI(); 
 
    } 
 
    private void initUI() { 
 
        XYDataset dataset = createDataset(); 
        JFreeChart chart = createChart(dataset); 
 
        ChartPanel chartPanel = new ChartPanel(chart); 
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
        chartPanel.setBackground(Color.white); 
        chartPanel.setPreferredSize(new Dimension(650,400)); 
        add(chartPanel); 
    } 
     
     
    
 
    public abstract XYDataset createDataset(); 
 
    public abstract JFreeChart createChart(XYDataset dataset); 
}