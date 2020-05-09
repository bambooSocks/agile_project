package rcm.ui.journey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;
import rcm.ui.UpdatablePanel;

public abstract class BaseJourneyView extends BaseView {

    private static final long serialVersionUID = -7158594990405366048L;
    protected int journey_id = -1;
    protected Journey journey;
    protected List<ContainerStatus> statuses;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    protected BaseGraph tempGraph, pressureGraph, humidityGraph;
    protected JTable locationTable;
    private JPanel tablePanel;
    protected UpdatablePanel contentLabelsPanel;

    protected BaseJourneyView(Application app, BaseTopBar topBar) {
        super(app, topBar);
        journey = app.getJourneyById(journey_id);
    }

    @Override
    protected Component buildContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Journey Details"));

        GridBagConstraints cMain = new GridBagConstraints();
        cMain.insets = new Insets(10, 10, 10, 10);
        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.gridx = 0;
        cMain.gridy = 0;
        cMain.ipady = 10;
        contentLabelsPanel = new UpdatablePanel() {
            private static final long serialVersionUID = 5467971044456088127L;

            @Override
            public JPanel buildContent() {
                return buildLabelsJourney();
            }
        };
        contentLabelsPanel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(contentLabelsPanel, cMain);

        cMain.fill = GridBagConstraints.WEST;
        cMain.gridx = 1;
        cMain.gridy = 0;
        cMain.weighty = 0;
        panel.add(buildRightButton(), cMain);

        cMain.gridx = 0;
        cMain.gridy = 2;
        cMain.gridwidth = 3;
        cMain.fill = GridBagConstraints.HORIZONTAL;
        cMain.weightx = 1.0;
        JLabel titleStatus;

        if (statuses == null) {
            titleStatus = new JLabel("NO STATUS YET");
        } else {
            titleStatus = new JLabel("CONTAINER STATUS");
        }

        titleStatus.setFont(new Font("", Font.PLAIN, 18));
        titleStatus.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleStatus, cMain);

        cMain.gridx = 0;
        cMain.gridy = 3;
        cMain.gridwidth = 3;
        tempGraph = new GraphTemperature(app, journey_id) {
            private static final long serialVersionUID = 123123123L;

            @Override
            public List<ContainerStatus> getStatus(int journey_id) {
                return requestStatus();
            }
        };
        tempGraph.getChartPanel().setPreferredSize(new Dimension(650, 400));
        panel.add(tempGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 4;
        cMain.gridwidth = 3;
        pressureGraph = new GraphPressure(app, journey_id) {
            private static final long serialVersionUID = 123121353123L;

            @Override
            public List<ContainerStatus> getStatus(int journey_id) {
                return requestStatus();
            }
        };
        pressureGraph.getChartPanel().setPreferredSize(new Dimension(650, 400));
        panel.add(pressureGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 5;
        cMain.gridwidth = 3;
        humidityGraph = new GraphHumidity(app, journey_id) {
            private static final long serialVersionUID = 23678123123123L;

            @Override
            public List<ContainerStatus> getStatus(int journey_id) {
                return requestStatus();
            }
        };
        humidityGraph.getChartPanel().setPreferredSize(new Dimension(650, 400));
        panel.add(humidityGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 6;
        cMain.gridwidth = 2;
        cMain.ipady = 110;
        cMain.weightx = 0.0;
        tablePanel = buildLocationTable();
        panel.add(tablePanel, cMain);

        if (statuses == null) {
            tempGraph.setVisible(false);
            pressureGraph.setVisible(false);
            humidityGraph.setVisible(false);
            tablePanel.setVisible(false);
        } else {
            tempGraph.setVisible(true);
            pressureGraph.setVisible(true);
            humidityGraph.setVisible(true);
            tablePanel.setVisible(true);
        }

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return (scroll);
    }

    protected abstract JPanel buildRightButton();

    protected JPanel buildLabelsJourney() {
        JPanel leftSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        JLabel labelTitle, labelOrigin, labelDestination, labelContent, testID, testOrigin, testDestination,
                testContent, label5, label6, startDate, endDate;

        labelTitle = new JLabel("Journey: ");
        labelTitle.setFont(new Font("", Font.BOLD, 16));
        labelOrigin = new JLabel("Origin: ");
        labelOrigin.setFont(new Font("", Font.BOLD, 14));
        labelDestination = new JLabel("Destination: ");
        labelDestination.setFont(new Font("", Font.BOLD, 14));
        labelContent = new JLabel("Content: ");
        labelContent.setFont(new Font("", Font.BOLD, 14));

        testID = new JLabel(String.valueOf(journey_id));
        testID.setFont(new Font("", Font.ITALIC, 16));
        testID.setForeground(new Color(255, 0, 0));

        if (journey != null) {
            testOrigin = new JLabel(journey.getOriginPort());
            testDestination = new JLabel(journey.getDestinationPort());
            testContent = new JLabel(journey.getContent());
        } else {
            testOrigin = new JLabel("unknown");
            testDestination = new JLabel("unknown");
            testContent = new JLabel("unknown");
        }

        testOrigin.setFont(new Font("", Font.ITALIC, 14));
        testDestination.setFont(new Font("", Font.ITALIC, 14));
        testContent.setFont(new Font("", Font.ITALIC, 14));

        label5 = new JLabel("Start Date: ");
        label5.setFont(new Font("", Font.BOLD, 14));
        label6 = new JLabel("End Date: ");
        label6.setFont(new Font("", Font.BOLD, 14));
        if (journey.getStartTimestamp() == null) {
            startDate = new JLabel("not started yet");
        } else {
            startDate = new JLabel(journey.getStartTimestamp().format(formatter));
        }

        startDate.setFont(new Font("", Font.ITALIC, 14));
        if (journey.getEndTimestamp() == null) {
            endDate = new JLabel("not ended yet");
        } else {
            endDate = new JLabel(journey.getEndTimestamp().format(formatter));
        }
        endDate.setFont(new Font("", Font.ITALIC, 14));

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
        c.gridx = 2;
        c.gridy = 1;
        leftSide.add(label5, c);
        c.gridx = 3;
        c.gridy = 1;
        leftSide.add(startDate, c);
        c.gridx = 2;
        c.gridy = 2;
        leftSide.add(label6, c);
        c.gridx = 3;
        c.gridy = 2;
        leftSide.add(endDate, c);

        return leftSide;
    }

    protected JPanel buildLocationTable() {
        locationTable = new JTable();
        locationTable.setPreferredScrollableViewportSize(new Dimension(600, 160));
        JScrollPane spTable = new JScrollPane(locationTable);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Location tracking",
                TitledBorder.CENTER, TitledBorder.TOP));
        panel.add(spTable, BorderLayout.CENTER);
        return panel;
    }

    protected void updateLocationTable() {

        DefaultTableModel tableModel = (DefaultTableModel) locationTable.getModel();
        tableModel.setRowCount(0);

        String[] columnNames = { "Date and Time", "Location" };
        tableModel.setColumnIdentifiers(columnNames);

        if (statuses != null) {
            for (ContainerStatus s : statuses) {
                String date = s.getTimestamp().format(formatter);
                String location = s.getLocation();
                Object[] rowData = { date, location };
                tableModel.addRow(rowData);
            }
            tablePanel.setVisible(true);
        } else {
            tablePanel.setVisible(false);
        }

        locationTable.setModel(tableModel);
        locationTable.setEnabled(false);
        tableModel.fireTableDataChanged();
    }

    protected abstract List<ContainerStatus> requestStatus();

    public void setJourneyID(int journeyID) {
        this.journey_id = journeyID;
    }
}
