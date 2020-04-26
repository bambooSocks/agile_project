package rcm.ui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import rcm.model.Application;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;


public class MainView extends JFrame {

    private static final long serialVersionUID = 4076136046104406648L;

    LogInView lv;
    CompanyTabView co;
    ClientTabView cl;
    
    Application app;
    
    public MainView(Application app) {
        super("Remote Container Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.app = app;
        
        lv = new LogInView(app);
        co = new CompanyTabView(app);
        cl = new ClientTabView(app);
        
        setLayout(new CardLayout());
        add(lv);
        add(co);
        add(cl);
    }
    
    public void switchCard(ViewCardType vc) {
        switch (vc) {
        case LOGIN:
            lv.setVisible(true);
            co.setVisible(false);
            cl.setVisible(false);
            break;
        case COMPANY:
            lv.setVisible(false);
            co.setVisible(true);
            cl.setVisible(false);
            break;
        case CLIENT:
            lv.setVisible(false);
            co.setVisible(false);
            cl.setVisible(true);
            break;
        default:    
        }
        
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

    public static void main(String[] args) {
        Repository repo = new SqliteRepository();
        Application app = new Application(repo);
        MainView mv = new MainView(app);
        mv.run();
    }
    
}
