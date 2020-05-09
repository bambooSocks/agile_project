package rcm.ui;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JFrame;

import rcm.config.FakeData;
import rcm.model.Application;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class MainView extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 4076136046104406648L;

    private LogInView lv;
    private CompanyTabView co;
    private ClientTabView cl;
    private ClientMenuBar menuBarcl;
    private CompanyMenuBar menuBarco;

    @SuppressWarnings("unused")
    private Application app;

    public MainView(Application app) {
        super("Remote Container Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.app = app;
        app.addObserver(this);

        lv = new LogInView(app);
        co = new CompanyTabView(app);
        cl = new ClientTabView(app);

        menuBarcl = new ClientMenuBar(app);
        menuBarco = new CompanyMenuBar(app);
        setLayout(new CardLayout());
        add(lv);
        add(co);
        add(cl);
    }

    public void run() {
        pack();
        setVisible(true);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "userLoggedOut":
            lv.setVisible(true);
            co.setVisible(false);
            cl.setVisible(false);
            menuBarco.setVisible(false);
            menuBarcl.setVisible(false);
            break;
        case "companyLoggedIn":
            lv.setVisible(false);
            co.setVisible(true);
            cl.setVisible(false);
            setJMenuBar(menuBarco);
            menuBarco.setVisible(true);
            break;
        case "clientLoggedIn":
            lv.setVisible(false);
            co.setVisible(false);
            cl.setVisible(true);
            setJMenuBar(menuBarcl);
            menuBarcl.setVisible(true);
            break;
        default:
            break;
        }
    }

    public static void main(String[] args) throws IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        Application app = new Application(repo);
        MainView mv = new MainView(app);
        try {
            FakeData.setupFakeApp(app);
            // Company 1
//            app.logInUser("peter@agrofert.dk", "Password12345");
            // Company 2
//            app.logInUser("christian@3maersk.dk", "Password12345");
            // Client user from company 1
            app.logInUser("tom@cbs.dk", "Password12345");  
            // Client user from company 2
//            app.logInUser("gardener@shire.com", "Password12345");
        } catch (WrongInputException e) {
        }
        mv.run();
    }
}
