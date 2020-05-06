package rcm.ui.info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Journey;
import rcm.ui.BaseTopBar;
import rcm.ui.popup.Dialog;

class ClientsInfoTopBar extends BaseTopBar {

    private static final long serialVersionUID = 3449416620350309737L;

    public ClientsInfoTopBar(Application app) {
        super(app, true);
    }

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new BorderLayout());
        JButton backButton = new JButton(" < ");
        backButton.setFont(new Font("Serif", Font.BOLD, 12));
        backButton.setPreferredSize(new Dimension(50, 30));
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                app.switchCards("ClientsTable");
            }
        });
        leftSide.add(backButton, BorderLayout.WEST);
        return leftSide;
    }

}

public class ClientsInfoView extends BaseInfoView {

    private static final long serialVersionUID = 2372110313751254716L;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private int client_id = -1;
    protected JPopupMenu popupMenu;
    protected JMenuItem itemViewJourney;

    public ClientsInfoView(Application app) {
        super(app, new ClientsInfoTopBar(app));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "showClient":
            updateTableModel();
            break;
        default:
            break;
        }
    }

    @Override
    public void updateTableModel() {
        if (app.getLoggedInCompany() != null) {

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
            tableModel.setColumnIdentifiers(columnNames);

            List<Journey> journeys = app.requestClientsJourneys(client_id);

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

            popupMenu = new JPopupMenu();
            itemViewJourney = new JMenuItem("View Journey");
            popupMenu.add(itemViewJourney);
            table.setComponentPopupMenu(popupMenu);
            table.setModel(tableModel);

            itemViewJourney.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        app.showCompanyJourney(id);
                    } catch (Exception e) {
                        Dialog.WarningDialog("Please choose a journey first", "No journey chosen");
                    }
                }
            });

            tableModel.fireTableDataChanged();
        }
    }

    @Override
    public JPanel buildInfoPanel() {
        JPanel infoPanel = new JPanel();
        Client client = app.getClientById(client_id);
        // TODO: change for proper code
        infoPanel.add(new JLabel("" + client_id));
        return infoPanel;
    }

    public void setClientID(int id) {
        client_id = id;
    }

}
