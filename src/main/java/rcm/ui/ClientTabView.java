package rcm.ui;

import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rcm.model.Application;
import rcm.ui.tables.MyJourneysTableView;
import rcm.ui.tables.SharedJourneysTableView;

public class ClientTabView extends JTabbedPane {

    private static final long serialVersionUID = 4767770251924620192L;

    public ClientTabView(Application app) {
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                app.clientTabChanged();
            }
        });
        addTab("My Journeys", new MyJourneysTableView(app));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Shared Journeys", new SharedJourneysTableView(app));
        setMnemonicAt(1, KeyEvent.VK_2);

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
