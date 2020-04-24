package rcm.ui;

import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;

public class CompanyTabView extends JTabbedPane{

    private static final long serialVersionUID = -465926656508535026L;

    public CompanyTabView(MainViewController mvc) {
        addTab("Clients", new ClientsTableView(mvc));
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Containers", new ContainersTableView(mvc));
        setMnemonicAt(1, KeyEvent.VK_2);
                
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
}
