package rcm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import rcm.model.Application;

public class MenuBarCompany extends BaseMenuBar {

    private static final long serialVersionUID = 419633327918376856L;

    public MenuBarCompany(Application app) {
        super(app);
        
        JMenuItem shcl = new JMenuItem("Show Clients");
        shcl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.fireChange("showClientsTable");
            }
        });
        
        JMenuItem shco = new JMenuItem("Show Containers");
        shco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.fireChange("showContainersTable");
            }
        });
        
        view.add(shcl);
        view.add(shco);
        add(search);
        add(view);
    }
}
