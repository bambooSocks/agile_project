package rcm.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogInView extends JPanel {

    private static final long serialVersionUID = 704477873893995966L;
    
    public LogInView(MainViewController mvc) {
        JLabel l = new JLabel("Temorary login");
        JButton clBtn = new JButton("Login as Client");
        clBtn.addActionListener(mvc);
        clBtn.setActionCommand("CLIENT");
        JButton coBtn = new JButton("Login as Company");
        coBtn.addActionListener(mvc);
        coBtn.setActionCommand("COMPANY");
        
        add(l);
        add(clBtn);
        add(coBtn);
    }
}
