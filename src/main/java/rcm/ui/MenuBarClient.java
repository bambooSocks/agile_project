package rcm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import rcm.model.Application;

public class MenuBarClient extends BaseMenuBar {

    private static final long serialVersionUID = -9203846064650098332L;

    public MenuBarClient(Application app) {
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
        add(search);
        add(view);
    }

}
