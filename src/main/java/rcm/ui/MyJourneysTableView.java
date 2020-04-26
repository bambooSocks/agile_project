package rcm.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;

class MyJourneysTopBar extends BaseTopBar {

    public MyJourneysTopBar(Application app) {
        super(app);
    }

    private static final long serialVersionUID = -978144513412923606L;

    @Override
    public JPanel buildLeftSide() {
        JPanel leftSide = new JPanel(new FlowLayout());

        // New Journey button
        JButton newJourney = new JButton("New Journey");
        newJourney.setPreferredSize(new Dimension(150, 30));
        newJourney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJourneyView popup = new CreateJourneyView();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        leftSide.add(newJourney);

        return leftSide;
    }
    
}

//Created table for MyJourneys: will appear on MyJourneys tab
public class MyJourneysTableView extends BaseTableView {

    private static final long serialVersionUID = -8487746616760043744L;

    public MyJourneysTableView(Application app) {
        super(new MyJourneysTopBar(app));
    }

    @Override
    public String[] addColumnNames() {
        String[] columnNames = { "ID", "Origin", "Destination", "Content", "Start Date", "End Date" };
        return columnNames;
    }

    // TODO: Change addData method according to listener
    @Override
    public Object[][] addData() {
        Object[][] data = {
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Rio de Janeiro", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" } };
        return data;
    }

}
