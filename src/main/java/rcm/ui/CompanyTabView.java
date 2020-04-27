package rcm.ui;

import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;

import rcm.model.Application;

public class CompanyTabView extends JTabbedPane {

    private static final long serialVersionUID = -465926656508535026L;

    public CompanyTabView(Application app) {
        addTab("Clients", new ClientsTableView(app));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Containers", new ContainersTableView(app));
        setMnemonicAt(1, KeyEvent.VK_2);

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
