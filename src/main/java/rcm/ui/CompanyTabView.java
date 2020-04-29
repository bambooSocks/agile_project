package rcm.ui;

import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rcm.model.Application;
import rcm.ui.tables.ClientsTableView;
import rcm.ui.tables.ContainersTableView;

public class CompanyTabView extends JTabbedPane {

    private static final long serialVersionUID = -465926656508535026L;

    public CompanyTabView(Application app) {
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                app.companyTabChanged();
            }
        });
        addTab("Clients", new ClientsTableView(app));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Containers", new ContainersTableView(app));
        setMnemonicAt(1, KeyEvent.VK_2);

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
