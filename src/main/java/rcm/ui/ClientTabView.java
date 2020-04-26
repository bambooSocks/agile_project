package rcm.ui;


import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;

import rcm.model.Application;

public class ClientTabView extends JTabbedPane {
    
    private static final long serialVersionUID = 4767770251924620192L;

    public ClientTabView(Application app) {
        addTab("My Journeys", new MyJourneysTableView(app));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Shared Journeys", new SharedJourneysTableView(app));
        setMnemonicAt(1, KeyEvent.VK_2);
        
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
