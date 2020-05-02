package rcm.ui.tables;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.Client;

import rcm.ui.BaseTopBar;
import rcm.ui.popup.CreateClientView;

class ClientsTopBar extends BaseTopBar {

    public ClientsTopBar(Application app) {
        super(app);
    }

    private static final long serialVersionUID = -455443009885225672L;

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new FlowLayout());

        // New Client button
        JButton newClient = new JButton("New Client");
        newClient.setPreferredSize(new Dimension(150, 30));
        newClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateClientView popup = new CreateClientView();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        leftSide.add(newClient);

        return leftSide;
    }

}

public class ClientsTableView extends BaseTableView implements ActionListener {

    private static final long serialVersionUID = -319420806707922265L;

    public ClientsTableView(Application app) {
        super(app, new ClientsTopBar(app));
    }

    @Override
    public void updateTableModel() {

        if (app.getLoggedInCompany() != null) {

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            String[] columnNames = { "ID", "Name", "Address", "Reference person", "Email" };
            tableModel.setColumnIdentifiers(columnNames);

            List<Client> clients = app.requestClients();

            for (int i = 0; i < clients.size(); i++) {
                Client c = clients.get(i);
                int dataId = c.getId();
                String dataName = c.getName();
                String dataAddress = c.getAddress();
                String dataRefPerson = c.getRefPerson();
                String dataEmail = c.getEmail();
                Object[] rowData = { dataId, dataName, dataAddress, dataRefPerson, dataEmail };
                tableModel.addRow(rowData);
            }

            table.setModel(tableModel);
            tableModel.fireTableDataChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "companyTabChanged":
        case "companyLoggedIn":
            updateTableModel();
            break;

        default:
            break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub

    }

}
