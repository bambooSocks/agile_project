package rcm.ui;
import javax.swing.*; 
public class BaseMenuBar extends JMenuBar { 

    /**
     * 
     */
    private static final long serialVersionUID = 3416869874550541750L;

 
  
    // JMenu 
    static JMenu prof,search,view; 
  
    // Menu items 
    static JMenuItem manage, logout; 
    
    
    public BaseMenuBar() {
        // create a menu 
        prof = new JMenu("Profile"); 
        search = new JMenu("Search"); 
        view = new JMenu("View"); 
  
        // create menuitems 
        manage = new JMenuItem("Manage profile"); 
        logout = new JMenuItem("Log out"); 
  
        // add menu items to menu 
        prof.add(manage); 
        prof.add(logout); 

  
        // add menu to menu bar 
        add(prof);  
 
    }
}

