package rcm.ui.tables;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;
import rcm.ui.BaseTopBar;

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

    public ContainersTableView(Application app) {
        super(app, new ContainersTopBar(app));
    }
//
//    @Override
//    public String[] addColumnNames() {
//        String[] columnNames = { "ID", "Current state", "Last Client" };
//        return columnNames;
//    }
//
//    // TODO: Change addData method according to listener
//    @Override
//    public Object[][] addData() {
//        Object[][] data = { { "new Integer(1)", "available", "Maersk" }, { "new Integer(1)", "available", "Maersk" },
//                { "new Integer(1)", "available", "Maersk" }, { "new Integer(1)", "available", "Maersk" },
//                { "new Integer(1)", "available", "Maersk" } };
//        return data;
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }

}
