package rcm.ui.tables;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import rcm.model.Application;
import rcm.model.Container;
import rcm.ui.BaseTopBar;
import rcm.ui.popup.Dialog;

class ContainersTopBar extends BaseTopBar {

    public ContainersTopBar(Application app) {
        super(app);
    }

    private static final long serialVersionUID = -6291570981725621141L;

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new FlowLayout());

        // New Container button
        JButton newContainer = new JButton("New Container");
        newContainer.setPreferredSize(new Dimension(150, 30));
        newContainer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Container clicked");
            }
        });
        leftSide.add(newContainer);

        return leftSide;
    }

}

public class ContainersTableView extends BaseTableView {

    private static final long serialVersionUID = -3009522281466857043L;
    private Container pane;

    public ContainersTableView(Application app) {
        super(app, new ContainersTopBar(app));
        app.addObserver(this);
    }

    public void updateTableModel(Container pane) {

        if (app.getLoggedInCompany() != null) {
            this.pane = pane;

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            String[] columnNames = { "ID", "Current state" };
            tableModel.setColumnIdentifiers(columnNames);

            List<Container> containers = app.requestContainers();

            for (int i = 0; i < containers.size(); i++) {
                Container c = containers.get(i);
                int dataId = c.getId();
                String dataState = ((c.isAvailable(LocalDateTime.now())) ? "available" : "not available right now");
//                String dataLastJourney = ((c.getJourneyList() != null)) ? String.valueOf(c.getJourneyList().getLast().getId()) : "none";
                Object[] rowData = { dataId, dataState };
                tableModel.addRow(rowData);
            }

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem itemViewContainer = new JMenuItem("View Container");
            popupMenu.add(itemViewContainer);
            table.setComponentPopupMenu(popupMenu);
            table.setModel(tableModel);

            itemViewContainer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        app.fireChange("showContainer", id);
                    } catch (Exception e) {
                        Dialog.WarningDialog("Please choose a container first", "No container chosen");
                    }
                }
            });

            tableModel.fireTableDataChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "companyTabChanged":
        case "companyLoggedIn":
            updateTableModel(pane);
            break;
        default:
            break;
        }
    }

    public void updateTableModel() {
        // TODO Auto-generated method stub

    }

}
