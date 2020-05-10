package rcm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenuItem;

import rcm.model.Application;
import rcm.ui.popup.AdvancedJourneySearchView;

public class ClientMenuBar extends BaseMenuBar {

    private static final long serialVersionUID = -9203846064650098332L;
    private AdvancedJourneySearchView popup;
    private String cmd;

    public ClientMenuBar(Application app) {
        super(app);

        JMenuItem shmj = new JMenuItem("Show My Journeys");
        shmj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.fireChange("showMyJourneyTable");
            }
        });

        JMenuItem shsj = new JMenuItem("Show Shared Journeys");
        shsj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.fireChange("showSharedJourneyTable");
            }
        });

        view.add(shmj);
        view.add(shsj);

        JMenuItem advSearch = new JMenuItem("Advanced Search");
        advSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                popup = new AdvancedJourneySearchView(app);
                popup.setLocationRelativeTo(null);
                popup.setCommand(cmd);
                popup.setVisible(true);
            }
        });

        JMenuItem clearSearch = new JMenuItem("Clear Search");
        clearSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Object> filters = new HashMap<>();
                filters.put("query", "");
                filters.put("name", true);
                filters.put("origin", true);
                app.fireChange(cmd, filters);
            }
        });

        search.add(advSearch);
        search.add(clearSearch);
        add(search);
        add(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "clientLoggedIn":
            cmd = "advSearchMyJourneys";
            search.setEnabled(true);
            break;
        case "clientTabChanged":
            int idx = (int) evt.getNewValue();
            if (idx == 0) {
                cmd = "advSearchMyJourneys";
            } else if (idx == 1) {
                cmd = "advSearchSharedJourneys";
            }
            search.setEnabled(true);
            break;
        case "showMyJourneyTable":
            cmd = "advSearchMyJourneys";
            search.setEnabled(true);
            break;
        case "showSharedJourneyTable":
            cmd = "advSearchSharedJourneys";
            search.setEnabled(true);
            break;
        case "showJourney":
        case "showSharedJourney":
            search.setEnabled(false);
            break;
        default:
            break;
        }
    }
}