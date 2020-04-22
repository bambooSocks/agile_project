package rcm.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class ClientsTopBar extends BaseTopBar {

    private static final long serialVersionUID = -455443009885225672L;

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new FlowLayout());
        
        JButton newClient = new JButton("New Client");
        newClient.setPreferredSize(new Dimension(150, 30));
        newClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Client clicked");
            }
        });
        leftSide.add(newClient);
        
        return leftSide;
    }
    
}

public class ClientsTableView extends BaseTableView {
    
    private static final long serialVersionUID = -319420806707922265L;
    
    public ClientsTableView() {
        super(new ClientsTopBar());
    }
        
}
