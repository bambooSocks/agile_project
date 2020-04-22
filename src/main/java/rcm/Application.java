package rcm;

import javax.swing.JFrame;

import rcm.ui.BaseTableView;
import rcm.ui.ClientsTableView;

public class Application {
    
    public static void main(String[] args) {
        
        // TODO: change this for actual code
        JFrame f = new JFrame("Window");
        
        BaseTableView b = new ClientsTableView();
        
        f.add(b);
        f.pack();
        f.setVisible(true);
    }
    
}
