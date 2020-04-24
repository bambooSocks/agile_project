package rcm.ui;


import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;

public class ClientTabView extends JTabbedPane {
    
    private static final long serialVersionUID = 4767770251924620192L;

    public ClientTabView(MainViewController mvc) {
        addTab("My Journeys", new MyJourneysTableView(mvc));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Shared Journeys", new SharedJourneysTableView(mvc));
        setMnemonicAt(1, KeyEvent.VK_2);
        
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
