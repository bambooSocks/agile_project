package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;

/* 
 * LabelDemo.java needs one other file: 
 *   images/middle.gif 
 */
public class JourneyView extends JFrame {
    /** 
     *  
     */
    private static final long serialVersionUID = -8449997402400520468L;

    /** 
     *  
     */

    public JourneyView() {

        setLayout(new GridBagLayout());

        GridBagConstraints cMain = new GridBagConstraints();
        cMain.insets = new Insets(20, 20, 0, 20);

//        cMain.fill = GridBagConstraints.HORIZONTAL; 
//        cMain.gridx = 0; 
//        cMain.gridy = 0; 
//        cMain.weightx = 0.5; 
//        cMain.gridwidth = 1; 
//        add(buildTopSideJourney(), cMain); 

        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 1;
        cMain.weightx = 0.5;
        cMain.gridwidth = 1;
        add(buildLabelsJourney(), cMain);

        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 2;
        cMain.gridwidth = 6;
        cMain.ipady = 40; // make this component tall
        cMain.weightx = 0.0;
        add(buildTemperatureGraph(), cMain);

        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 3;
        cMain.gridwidth = 6;
        cMain.ipady = 40; // make this component tall
        cMain.weightx = 0.0;
        add(buildPressureGraph(), cMain);

        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 4;
        cMain.gridwidth = 6;
        cMain.ipady = 40; // make this component tall
        cMain.weightx = 0.0;
        add(buildHumidityGraph(), cMain);

        // TODO: make this an abstract method
        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 2;
        cMain.gridy = 1;
        cMain.weightx = 0.5;
        cMain.gridwidth = 2;
        add(buildTopLeftSide(), cMain);

        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 5;
        cMain.gridwidth = 6;
        cMain.ipady = 40;
        cMain.weightx = 0.0;
        add(buildLocationTable(), cMain);

        setPreferredSize(new Dimension(680, 800));

        Container c = getContentPane();
        JScrollPane scroll = new JScrollPane(c);
        setContentPane(scroll);
    }

    private JPanel buildTopLeftSide() {
        JPanel leftSide = new JPanel(new GridLayout(3, 1));

        // Share Journey button
        JButton shareJourney = new JButton("Share Journey");
        shareJourney.setFont(new Font("Serif", Font.ITALIC, 16));
        shareJourney.setPreferredSize(new Dimension(100, 30));
        shareJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Shared journey clicked");
            }
        });
        leftSide.add(shareJourney);

        return leftSide;
    }

    private GraphTemperature buildTemperatureGraph() {
        GraphTemperature graphTemperature = new GraphTemperature();

        return graphTemperature;
    }

    private GraphPressure buildPressureGraph() {
        GraphPressure graphPressure = new GraphPressure();

        return graphPressure;
    }

    private GraphHumidity buildHumidityGraph() {
        GraphHumidity graphHumidity = new GraphHumidity();

        return graphHumidity;
    }

    private JPanel buildLabelsJourney() {
        JPanel rightSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        JLabel label1, label2, label3, label4, label5, label6, testID, testOrigin, testDestination, testContent,
                startDate, endDate;

        // Create the first label.
        label1 = new JLabel("Journey: ");
        label1.setFont(new Font("Serif", Font.BOLD, 16));
        label2 = new JLabel("Origin: ");
        label2.setFont(new Font("Serif", Font.BOLD, 16));
        label3 = new JLabel("Destination: ");
        label3.setFont(new Font("Serif", Font.BOLD, 16));
        label4 = new JLabel("Content: ");
        label4.setFont(new Font("Serif", Font.BOLD, 16));
        label5 = new JLabel("Start Date: ");
        label5.setFont(new Font("Serif", Font.BOLD, 16));
        label6 = new JLabel("End Date: ");
        label6.setFont(new Font("Serif", Font.BOLD, 16));

        testID = new JLabel("# 1234");
        testID.setFont(new Font("Serif", Font.ITALIC, 16));
        testID.setForeground(new Color(255, 0, 0));
        testOrigin = new JLabel("Rio de Janeiro ");
        testOrigin.setFont(new Font("Serif", Font.ITALIC, 16));
        testDestination = new JLabel("Rotterdam ");
        testDestination.setFont(new Font("Serif", Font.ITALIC, 16));
        testContent = new JLabel("Tobacco ");
        testContent.setFont(new Font("Serif", Font.ITALIC, 16));
        startDate = new JLabel("13/03/2020 4:20 pm ");
        startDate.setFont(new Font("Serif", Font.ITALIC, 16));
        endDate = new JLabel("10/05/2020 4:20 pm ");
        endDate.setFont(new Font("Serif", Font.ITALIC, 16));

        // Add the labels.
        c.gridx = 0;
        c.gridy = 0;
        rightSide.add(label1, c);
        c.gridx = 1;
        c.gridy = 0;
        rightSide.add(testID, c);
        c.gridx = 0;
        c.gridy = 1;
        rightSide.add(label2, c);
        c.gridx = 1;
        c.gridy = 1;
        rightSide.add(testOrigin, c);
        c.gridx = 0;
        c.gridy = 2;
        rightSide.add(label3, c);
        c.gridx = 1;
        c.gridy = 2;
        rightSide.add(testDestination, c);
        c.gridx = 0;
        c.gridy = 3;
        rightSide.add(label4, c);
        c.gridx = 1;
        c.gridy = 3;
        rightSide.add(testContent, c);
        c.gridx = 0;
        c.gridy = 4;
        rightSide.add(label5, c);
        c.gridx = 1;
        c.gridy = 4;
        rightSide.add(startDate, c);
        c.gridx = 0;
        c.gridy = 5;
        rightSide.add(label6, c);
        c.gridx = 1;
        c.gridy = 5;
        rightSide.add(endDate, c);
        return rightSide;
    }

    private Component buildLocationTable() {

        String[] columnNames = { "Date and Time", "Location" };

        Object[][] data = { { "13/03/2020 4:20", "Shenzen" }, { "13/03/2020 4:20", "Hong Kong" },
                { "13/03/2020 4:20", "Instambul" }, { "13/03/2020 4:20", "Rotterdam" },
                { "13/03/2020 4:20", "Rotterdam" }, { "13/03/2020 4:20", "Rotterdam" } };

        JTable table = new JTable(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };

        table.setModel(tableModel);
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        JScrollPane spTable = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(spTable);
        return panel;
    }
//    private Component buildTopSideJourney() {
//        // TODO Auto-generated method stub
//        return null;
//    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event dispatch thread.
     */
    public static void main(String[] args) {

        JourneyView frame = new JourneyView();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
