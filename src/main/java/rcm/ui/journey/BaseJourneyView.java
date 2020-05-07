package rcm.ui.journey;

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
    protected int journeyID = -1;
    protected Journey j;
    protected List<ContainerStatus> statuses;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    protected BaseGraph tempGraph, pressureGraph, humidityGraph;
    protected JTable locationTable;
    protected UpdatablePanel dateLabelsPanel;
    protected UpdatablePanel contentLabelsPanel;



    protected BaseJourneyView(Application app, BaseTopBar topBar) {
        super(app, topBar);
        j = app.getJourneyById(journeyID);
        statuses = app.requestStatus(journeyID);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 550);
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
        contentLabelsPanel = new UpdatablePanel() {
            private static final long serialVersionUID = 5467971044456088127L;

            @Override
            public JPanel buildContent() {
                return buildLabelsJourney();
            }
        };
        panel.add(contentLabelsPanel, cMain);

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
        dateLabelsPanel = new UpdatablePanel() {
            private static final long serialVersionUID = 5467971044762088127L;

            @Override
            public JPanel buildContent() {
                return buildDateLabelsJourney();
            }
        };
        panel.add(dateLabelsPanel, cMain);

        cMain.gridx = 1;
        cMain.gridy = 2;
        JLabel titleStatus = new JLabel("CONTAINER STATUS");
        titleStatus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel.add(titleStatus, cMain);

        cMain.gridx = 0;
        cMain.gridy = 3;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        tempGraph=new GraphTemperature(app, journeyID);
        panel.add(tempGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 4;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        pressureGraph=new GraphPressure(app, journeyID);
        panel.add(pressureGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 5;
        cMain.gridwidth = 6;
        cMain.weightx = 0.5;
        humidityGraph = new GraphHumidity(app, journeyID);
        panel.add(humidityGraph, cMain);

        cMain.gridx = 0;
        cMain.gridy = 6;
        cMain.gridwidth = 6;
        cMain.ipady = 110;
        cMain.weightx = 0.0;
        JPanel tablePanel = buildLocationTable();
        panel.add(tablePanel, cMain);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return (scroll);
    }

    protected JPanel buildDateLabelsJourney() {
        JPanel rightSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        JLabel label5, label6, startDate, endDate;

        label5 = new JLabel("Start Date: ");
        label5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label6 = new JLabel("End Date: ");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 16));
        if (j.getStartTimestamp() == null) {
            startDate = new JLabel("not started yet");
        } else {
            startDate = new JLabel(j.getStartTimestamp().format(formatter));
        }

        startDate.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        if (j.getEndTimestamp() == null) {
            endDate = new JLabel("not ended yet");
        } else {
            endDate = new JLabel(j.getEndTimestamp().format(formatter));
        }

        endDate.setFont(new Font("Times New Roman", Font.ITALIC, 16));

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



    protected GraphPressure buildPressureGraph() {
        GraphPressure graphPressure = new GraphPressure(app, journeyID);

        return graphPressure;
    }

    protected GraphHumidity buildHumidityGraph() {
        GraphHumidity graphHumidity = new GraphHumidity(app, journeyID);

        return graphHumidity;
    }

    protected JPanel buildLabelsJourney() {
        JPanel leftSide = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        JLabel labelTitle, labelOrigin, labelDestination, labelContent, testID, testOrigin, testDestination,
                testContent;

        labelTitle = new JLabel("Journey: ");
        labelTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        labelOrigin = new JLabel("Origin: ");
        labelOrigin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        labelDestination = new JLabel("Destination: ");
        labelDestination.setFont(new Font("Times New Roman", Font.BOLD, 16));
        labelContent = new JLabel("Content: ");
        labelContent.setFont(new Font("Times New Roman", Font.BOLD, 16));

        testID = new JLabel(String.valueOf(journeyID));
        testID.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        testID.setForeground(new Color(255, 0, 0));
        
        if (j != null) {
            testOrigin = new JLabel(j.getOriginPort());
            testDestination = new JLabel(j.getDestinationPort());
            testContent = new JLabel(j.getContent());
        } else {
            testOrigin = new JLabel("unknown");
            testDestination = new JLabel("unknown");
            testContent = new JLabel("unknown");
        }
        
        testOrigin.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        testDestination.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        testContent.setFont(new Font("Times New Roman", Font.ITALIC, 16));

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

    protected JPanel buildLocationTable() {
        locationTable = new JTable();
        locationTable.setPreferredScrollableViewportSize(new Dimension(600, 160));
        JScrollPane spTable = new JScrollPane(locationTable);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Location tracking",
                TitledBorder.CENTER, TitledBorder.TOP));
        panel.add(spTable);
        return panel;
        
    }
    protected void updateLocationTable(JTable table, Application app, int id) {
        
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        String[] columnNames = { "Date and Time", "Location"};
        tableModel.setColumnIdentifiers(columnNames);

        statuses = app.requestStatus(id);
        
        for (ContainerStatus s : statuses) {
            String date = s.getTimestamp().format(formatter);
            String location = s.getLocation();
            Object[] rowData = { date, location };
            tableModel.addRow(rowData);
        }

        table.setModel(tableModel);
        table.setEnabled(false);
        tableModel.fireTableDataChanged();
    }

    public int getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(int journeyID) {
        this.journeyID = journeyID;
    }

}
