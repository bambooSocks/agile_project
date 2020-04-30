package rcm.ui;

import javax.swing.JMenuItem;

public class MenuBarClient extends BaseMenuBar{

    private static final long serialVersionUID = -9203846064650098332L;

    public MenuBarClient() {
        super();
        JMenuItem shcl = new JMenuItem("Show My Journeys"); 
        JMenuItem shco = new JMenuItem("Show Shared Journeys");  
        view.add(shcl); 
        view.add(shco);
        add(search); 
        add(view);
    }

}
