package rcm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JMenuItem;

import rcm.model.Application;
import rcm.ui.popup.AdvancedClientSearchView;
import rcm.ui.popup.AdvancedJourneySearchView;
import rcm.ui.popup.BaseAdvancedSearchView;

public class CompanyMenuBar extends BaseMenuBar {

    private static final long serialVersionUID = 419633327918376856L;
    private BaseAdvancedSearchView popup;
    private String cmd;

    public CompanyMenuBar(Application app) {
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

        JMenuItem advSearch = new JMenuItem("Advanced Search");
        advSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                popup.setLocationRelativeTo(null);
                popup.setCommand(cmd);
                popup.setVisible(true);
            }
        });

        search.add(advSearch);
        add(search);
        add(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "companyLoggedIn":
        case "showClientsTable":
            popup = new AdvancedClientSearchView(app);
            cmd = "advSearchClients";
            search.setEnabled(true);
            break;
        case "companyTabChanged":
            int idx = (int) evt.getNewValue();
            if (idx == 0) {
                popup = new AdvancedClientSearchView(app);
                cmd = "advSearchClients";
                search.setEnabled(true);
            } else if (idx == 1) {
                search.setEnabled(false);
            }
            break;
        case "showCompanyJourney":
        case "showContainersTable":
            search.setEnabled(false);
            break;
        case "updateContainer":
        case "showContainer":
            popup = new AdvancedJourneySearchView(app);
            cmd = "advSearchContainersJourney";
            search.setEnabled(true);
            break;
        case "updateClient":
        case "showClient":
            popup = new AdvancedJourneySearchView(app);
            cmd = "advSearchClientsJourney";
            search.setEnabled(true);
            break;
        default:
            break;
        }
    }
}
