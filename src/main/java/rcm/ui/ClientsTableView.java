package rcm.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class ClientsTopBar extends BaseTopBar {

    public ClientsTopBar(MainViewController mvc) {
        super(mvc);
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
                System.out.println("New Client clicked");
            }
        });
        leftSide.add(newClient);

        return leftSide;
    }

}

public class ClientsTableView extends BaseTableView {

    private static final long serialVersionUID = -319420806707922265L;

    public ClientsTableView(MainViewController mvc) {
        super(new ClientsTopBar(mvc));
        
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
