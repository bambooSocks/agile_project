package rcm.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class MainViewController implements ActionListener {

    private MainView mv;
    
    public MainViewController(MainView mv) {
        this.mv = mv;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "LOGOUT":
            mv.getLogInView().setVisible(true);
            mv.getClientView().setVisible(false);
            mv.getCompanyView().setVisible(false);
            break;
        case "CLIENT":
            mv.getLogInView().setVisible(false);
            mv.getClientView().setVisible(true);
            mv.getCompanyView().setVisible(false);
            break;
        case "COMPANY":
            mv.getLogInView().setVisible(false);
            mv.getClientView().setVisible(false);
            mv.getCompanyView().setVisible(true);
            break;
        default:    
        }
        
    }
    
}

public class MainView extends JFrame {

    private static final long serialVersionUID = 4076136046104406648L;

    LogInView lv;
    CompanyTabView co;
    ClientTabView cl;
    MainViewController mvc;
    
    public MainView() {
        super("Remote Container Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mvc = new MainViewController(this);
        
        lv = new LogInView(mvc);
        co = new CompanyTabView(mvc);
        cl = new ClientTabView(mvc);
        
        setLayout(new CardLayout());
        add(lv);
        add(co);
        add(cl);
    }
    
    public void run() {
        pack();
        setVisible(true);
    }

    public LogInView getLogInView() {
        return lv;
    }

    public CompanyTabView getCompanyView() {
        return co;
    }

    public ClientTabView getClientView() {
        return cl;
    }

}
