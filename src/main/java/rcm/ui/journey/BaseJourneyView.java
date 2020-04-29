package rcm.ui.journey;

import java.awt.Color;
import java.awt.Component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;

public abstract class BaseJourneyView extends BaseView {

    private static final long serialVersionUID = -7158594990405366048L;

    protected BaseJourneyView(Application app, BaseTopBar topBar) {
        super(app, topBar);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 800);
    }

    @Override
    protected Component buildContent() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        GridBagConstraints cMain = new GridBagConstraints();
        cMain.insets = new Insets(10, 20, 10, 10);
        cMain.fill = GridBagConstraints.HORIZONTAL;

        cMain.gridx = 0;
        cMain.gridy = 0;
        cMain.gridheight = 2;
        cMain.ipady = 10;
        panel.add(buildLabelsJourney(), cMain);

        cMain.gridwidth = 2;
        cMain.gridx = 3;
        cMain.gridy = 0;
        cMain.weighty = 0;
        cMain.gridheight = 1;
        panel.add(buildRightButton(), cMain);

        cMain.anchor = GridBagConstraints.LAST_LINE_END;
        cMain.gridx = 1;
        cMain.gridy = 0;
        cMain.gridwidth = 1;
        panel.add(buildDateLabelsJourney(), cMain);

        cMain.gridx = 1;
        cMain.gridy = 2;
        JLabel titleStatus = new JLabel("CONTAINER STATUS");
        titleStatus.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(titleStatus, cMain);

        cMain.gridx = 0;
        cMain.gridy = 3;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        panel.add(buildTemperatureGraph(), cMain);

        cMain.gridx = 0;
        cMain.gridy = 4;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        panel.add(buildPressureGraph(), cMain);

        cMain.gridx = 0;
        cMain.gridy = 5;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        panel.add(buildHumidityGraph(), cMain);

        cMain.gridx = 0;
        cMain.gridy = 6;
        cMain.gridwidth = 6;
        cMain.ipady = 110;
        cMain.weightx = 0.0;
        panel.add(buildLocationTable(), cMain);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return (scroll);
    }

    private Component buildDateLabelsJourney() {
        JPanel rightSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        JLabel label5, label6, startDate, endDate;

        label5 = new JLabel("Start Date: ");
        label5.setFont(new Font("Serif", Font.BOLD, 16));
        label6 = new JLabel("End Date: ");
        label6.setFont(new Font("Serif", Font.BOLD, 16));

        startDate = new JLabel("13/03/2020 4:20 pm ");
        startDate.setFont(new Font("Serif", Font.ITALIC, 16));
        endDate = new JLabel("10/05/2020 4:20 pm ");
        endDate.setFont(new Font("Serif", Font.ITALIC, 16));

        c.gridx = 0;
        c.gridy = 0;
        rightSide.add(label5, c);
        c.gridx = 1;
        c.gridy = 0;
        rightSide.add(startDate, c);
        c.gridx = 0;
        c.gridy = 1;
        rightSide.add(label6, c);
        c.gridx = 1;
        c.gridy = 1;
        rightSide.add(endDate, c);

        return rightSide;
    }

    protected abstract JPanel buildRightButton();

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
        JPanel leftSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        JLabel labelTitle, labelOrigin, labelDestination, labelContent, testID, testOrigin, testDestination,
                testContent;

        labelTitle = new JLabel("Journey: ");
        labelTitle.setFont(new Font("Serif", Font.BOLD, 20));
        labelOrigin = new JLabel("Origin: ");
        labelOrigin.setFont(new Font("Serif", Font.BOLD, 16));
        labelDestination = new JLabel("Destination: ");
        labelDestination.setFont(new Font("Serif", Font.BOLD, 16));
        labelContent = new JLabel("Content: ");
        labelContent.setFont(new Font("Serif", Font.BOLD, 16));

        testID = new JLabel("# 1234");
        testID.setFont(new Font("Serif", Font.ITALIC, 20));
        testID.setForeground(new Color(255, 0, 0));
        testOrigin = new JLabel("Rio de Janeiro ");
        testOrigin.setFont(new Font("Serif", Font.ITALIC, 16));
        testDestination = new JLabel("Rotterdam ");
        testDestination.setFont(new Font("Serif", Font.ITALIC, 16));
        testContent = new JLabel("Tobacco ");
        testContent.setFont(new Font("Serif", Font.ITALIC, 16));

        // Add the labels.
        c.gridx = 0;
        c.gridy = 0;
        leftSide.add(labelTitle, c);
        c.gridx = 1;
        c.gridy = 0;
        leftSide.add(testID, c);
        c.gridx = 0;
        c.gridy = 1;
        leftSide.add(labelOrigin, c);
        c.gridx = 1;
        c.gridy = 1;
        leftSide.add(testOrigin, c);
        c.gridx = 0;
        c.gridy = 2;
        leftSide.add(labelDestination, c);
        c.gridx = 1;
        c.gridy = 2;
        leftSide.add(testDestination, c);
        c.gridx = 0;
        c.gridy = 3;
        leftSide.add(labelContent, c);
        c.gridx = 1;
        c.gridy = 3;
        leftSide.add(testContent, c);

        return leftSide;
    }

    private Component buildLocationTable() {

        String[] columnNames = { "Date and Time", "Location" };

        Object[][] data = { { "13/03/2020 4:20", "Shenzen" }, { "13/03/2020 4:20", "Hong Kong" },
                { "13/03/2020 4:20", "Instambul" }, { "13/03/2020 4:20", "Rotterdam" },
                { "13/03/2020 4:20", "Rotterdam" }, { "13/03/2020 4:20", "Rotterdam" },
                { "13/03/2020 4:20", "Rotterdam" }, { "13/03/2020 4:20", "Rotterdam" },
                { "13/03/2020 4:20", "Rotterdam" }, { "13/03/2020 4:20", "Rotterdam" },
                { "13/03/2020 4:20", "Rotterdam" } };

        JTable table = new JTable(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(tableModel);
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 160));
        JScrollPane spTable = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Location tracking",
                TitledBorder.CENTER, TitledBorder.TOP));
        panel.add(spTable);
        return panel;
    }

}
