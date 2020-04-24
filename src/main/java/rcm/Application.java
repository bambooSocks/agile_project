package rcm;

import java.util.LinkedList;
import java.util.List;

import rcm.model.*;
import rcm.ui.ErrorDialog;
import rcm.ui.MainView;
import rcm.ui.ViewCardType;

public class Application {

    private List<LogisticsCompany> system;
    private MainView mv;

    private List<User> getUsers() {
        List<User> users = new LinkedList<User>();
        users.addAll(system);
        users.addAll(getClients());
        return users;
    }

    private List<Client> getClients() {
        List<Client> clients = new LinkedList<Client>();
        for (LogisticsCompany c : system) {
            clients.addAll(c.getClients());
        }
        return clients;
    }

    public Application() {
        // TODO: for the Group2
//      system = loadFromDB();
        system = new LinkedList<LogisticsCompany>();

        try {
            LogisticsCompany lc = new LogisticsCompany("Oop", "plop", "Doop", "doop@oop.plop", "Pl000p");
            system.add(lc);
            lc.createClient("Bloop", "plop", "Gloop", "gloop@bloop.plop", "Pl000p");
            
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void loginUser(String email, char[] password) {
        // TODO: Adrienne min value
        String pswd = new String(password);

        boolean loginStatus = false;

        User loggedInUser = null;
        for (User u : getUsers()) {
            try {
                loginStatus = u.logInStatus(email, pswd);
                if (loginStatus) {
                    loggedInUser = u;
                    break;
                }
            } catch (WrongInputException e) {
                new ErrorDialog(e.getMessage(), "Login error");
                return;
            }
        }

        if (!loginStatus) {
            new ErrorDialog("Email not found", "Login error");
        } else {
            final String u = loggedInUser.getEmail();
            List<Client> cc = getClients();
            if (cc.stream().anyMatch(c -> c.getEmail().equals(u))) {
                switchMainViewTo(ViewCardType.CLIENT);
            } else {
                switchMainViewTo(ViewCardType.COMPANY);
            }

        }

    }

    public void switchMainViewTo(ViewCardType type) {
        mv.switchCard(type);
    }

    public static void main(String[] args) {
        Application app = new Application();
        MainView mv = new MainView(app);
        mv.run();
    }

    public void connectMainView(MainView mainView) {
        mv = mainView;
    }

}
