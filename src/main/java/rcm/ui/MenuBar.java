package rcm.ui;
import javax.swing.*; 
public class MenuBar extends JMenuBar { 

    /**
     * 
     */
    private static final long serialVersionUID = 3416869874550541750L;

 
  
    // JMenu 
    static JMenu x,x2,x3; 
  
    // Menu items 
    static JMenuItem m1, m2, m3,m4; 
    
    
    public MenuBar() {
        // create a menu 
        x = new JMenu("Profile"); 
        x2 = new JMenu("Search"); 
        x3 = new JMenu("View"); 
  
        // create menuitems 
        m1 = new JMenuItem("Manage profile"); 
        m2 = new JMenuItem("Log out"); 
        
        
        m3 = new JMenuItem("Show Clients"); 
        m4 = new JMenuItem("Show Containers");  
  
        // add menu items to menu 
        x.add(m1); 
        x.add(m2); 
        x3.add(m3); 
        x3.add(m4);
  
        // add menu to menu bar 
        add(x); 
        add(x2); 
        add(x3); 
 
    }
}

