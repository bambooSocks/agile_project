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
            mv.getLoginView().setVisible(true);
            mv.getClientView().setVisible(false);
            mv.getCompanyView().setVisible(false);
            break;
        case "CLIENT":
            mv.getLoginView().setVisible(false);
            mv.getClientView().setVisible(true);
            mv.getCompanyView().setVisible(false);
            break;
        case "COMPANY":
            mv.getLoginView().setVisible(false);
            mv.getClientView().setVisible(false);
            mv.getCompanyView().setVisible(true);
            break;
        default:    
        }
        
    }
    
}

public class MainView extends JFrame {

    private static final long serialVersionUID = 4076136046104406648L;

    LoginView lv;
    CompanyTabView co;
    ClientTabView cl;
    MainViewController mvc;
    
    public MainView() {
        super("Remote Container Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mvc = new MainViewController(this);
        
        lv = new LoginView(mvc);
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

    public LoginView getLoginView() {
        return lv;
    }

    public CompanyTabView getCompanyView() {
        return co;
    }

    public ClientTabView getClientView() {
        return cl;
    }

}
