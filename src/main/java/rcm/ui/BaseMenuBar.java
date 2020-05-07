package rcm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import rcm.model.Application;
import rcm.ui.popup.ProfileView;

public class BaseMenuBar extends JMenuBar {

    private static final long serialVersionUID = 3416869874550541750L;

    protected JMenu prof, search, view;
    private JMenuItem manage, logout;
    protected Application app;

    public BaseMenuBar(Application app) {
        this.app = app;
        
        // create a menu
        prof = new JMenu("Profile");
        search = new JMenu("Search");
        view = new JMenu("View");

        // create menu items
        manage = new JMenuItem("Manage profile");
        manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileView popup = new ProfileView(app);
                
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        
        logout = new JMenuItem("Log out");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.logOut();
            }
        });

        // add menu items to menu
        prof.add(manage);
        prof.add(logout);

        // add menu to menu bar
        add(prof);

    }
}
