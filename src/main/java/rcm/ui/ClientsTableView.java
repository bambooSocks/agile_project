package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

class ClientsTopBar extends BaseTopBar {

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

// Created table for MyJourneys: will appear on MyJourneys tab
class MyJourneysTable extends BaseTableView {

    private static final long serialVersionUID = 1L;

    @Override
    public String[] addcolumnNames() {
        String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
        return columnNames;
    }

    // TODO: Change addData method according to listener
    @Override
    public Object[][] addData() {
        Object[][] data = {
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" } };
        return data;
    }

}

//Created table for SharedJourneys: will appear on SharedJourneys tab
class SharedJourneysTable extends BaseTableView {

    private static final long serialVersionUID = 1L;

    @Override
    public String[] addcolumnNames() {
        String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
        return columnNames;
    }

// TODO: Change addData method according to listener
    @Override
    public Object[][] addData() {
        Object[][] data = {
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" } };
        return data;
    }

}

class MyJourneysClient extends JPanel {
    private static final long serialVersionUID = -319420806707922265L;

    public MyJourneysClient() {
        // TODO: Change Layout to BoxLayout?
        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);
        add(buildTableSide(), BorderLayout.CENTER);
    }

    // add topbar for Client
    private ClientsTopBar buildTopBar() {
        ClientsTopBar topbar = new ClientsTopBar();
        return topbar;
    }

    // add table:
    private MyJourneysTable buildTableSide() {
        MyJourneysTable table = new MyJourneysTable();
        return table;
    }

}

class SharedJourneysClient extends JPanel {
    private static final long serialVersionUID = -319420806707922265L;

    public SharedJourneysClient() {
        // TODO: Change Layout to BoxLayout?
        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);
        add(buildTableSide(), BorderLayout.CENTER);
    }

    // add topbar for Client
    private ClientsTopBar buildTopBar() {
        ClientsTopBar topbar = new ClientsTopBar();
        return topbar;
    }

    // add table:
    private SharedJourneysTable buildTableSide() {
        SharedJourneysTable table = new SharedJourneysTable();
        return table;
    }

}

public class ClientsTableView extends JPanel {

    private static final long serialVersionUID = -319420806707922265L;

    public ClientsTableView() {

        // Adding tabs which switch between panels
        // TODO Optional: Add an icon for each tab (implementable, saw in an example)
        JComponent panel1 = new MyJourneysClient();
        JComponent panel2 = new SharedJourneysClient();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("My Journeys", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Shared Journeys", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

}
