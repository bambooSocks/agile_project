package rcm.ui;

import javax.swing.JMenuItem;

public class MenuBarCompany extends BaseMenuBar {

    private static final long serialVersionUID = 419633327918376856L;

    public MenuBarCompany() {
        super();
        JMenuItem shcl = new JMenuItem("Show Clients"); 
        JMenuItem shco = new JMenuItem("Show Containers");  
        view.add(shcl); 
        view.add(shco);
        add(search); 
        add(view);
    }
}
