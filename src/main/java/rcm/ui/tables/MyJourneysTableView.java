package rcm.ui.tables;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Journey;
import rcm.ui.BaseTopBar;
import rcm.ui.popup.CreateJourneyView;

class MyJourneysTopBar extends BaseTopBar {

    public MyJourneysTopBar(Application app) {
        super(app);
    }

    private static final long serialVersionUID = -978144513412923606L;

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new FlowLayout());

        // New Journey button
        JButton newJourney = new JButton("New Journey");
        newJourney.setPreferredSize(new Dimension(150, 30));
        newJourney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJourneyView popup = new CreateJourneyView();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        leftSide.add(newJourney);

        return leftSide;
    }

}

public class MyJourneysTableView extends BaseTableView {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private static final long serialVersionUID = -8487746616760043744L;

    public MyJourneysTableView(Application app) {
        super(app, new MyJourneysTopBar(app));
        app.addObserver(this);
    }

    @Override
    public void updateTableModel() {
        if (app.getLoggedInClient() != null) {

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
            tableModel.setColumnIdentifiers(columnNames);

            List<Journey> journeys = app.requestJourneys();

            for (int i = 0; i < journeys.size(); i++) {
                Journey j = journeys.get(i);
                int dataId = j.getId();
                String dataOrigin = j.getOriginPort();
                String dataDestination = j.getDestinationPort();
                String dataContent = j.getContent();
                String dataStartDate = ((j.getStartTimestamp() != null) ? j.getStartTimestamp().format(formatter)
                        : "Not Started yet");
                String dataEndDate = ((j.getEndTimestamp() != null) ? j.getEndTimestamp().format(formatter)
                        : "Not Ended yet");
                Object[] rowData = { dataId, dataOrigin, dataDestination, dataContent, dataStartDate, dataEndDate };
                tableModel.addRow(rowData);
            }

            table.setModel(tableModel);
            tableModel.fireTableDataChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "clientTabChanged":
        case "clientLoggedIn":
            updateTableModel();
            break;

        default:
            break;
        }
    }

}
