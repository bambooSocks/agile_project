package rcm.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;

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

public class ClientsTableView extends BaseTableView {

    private static final long serialVersionUID = -319420806707922265L;

    public ClientsTableView(Application app) {
        super(new ClientsTopBar(app));

    }

    @Override
    public String[] addColumnNames() {
        String[] columnNames = { "ID", "Name", "Address", "Reference person", "Email" };
        return columnNames;
    }

    // TODO: Change addData method according to listener
    @Override
    public Object[][] addData() {
        Object[][] data = {
                { "new Integer(1)", "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com" },
                { "new Integer(1)", "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com" },
                { "new Integer(1)", "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com" },
                { "new Integer(1)", "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com" },
                { "new Integer(1)", "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com" } };
        return data;
    }

}
