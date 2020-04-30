package rcm.ui.tables;

import java.beans.PropertyChangeEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.Journey;
import rcm.ui.BaseTopBar;

class SharedJourneysTopBar extends BaseTopBar {

    public SharedJourneysTopBar(Application app) {
        super(app);
    }

    private static final long serialVersionUID = -2448342061117967555L;

    @Override
    public JPanel buildLeftSide() {
        return new JPanel();
    }

}

public class SharedJourneysTableView extends BaseTableView {
    
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private static final long serialVersionUID = 1156877669628672936L;

    public SharedJourneysTableView(Application app) {
        super(app, new SharedJourneysTopBar(app));
        app.addObserver(this);
    }

    @Override
    public void updateTableModel() {

        if (app.getLoggedInClient() != null) {

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
            tableModel.setColumnIdentifiers(columnNames);

            List<Journey> journeys = app.requestSharedJourneys();

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
