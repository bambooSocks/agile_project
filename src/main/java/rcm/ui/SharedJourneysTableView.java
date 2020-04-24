package rcm.ui;

import javax.swing.JPanel;

class SharedJourneysTopBar extends BaseTopBar {

    public SharedJourneysTopBar(MainViewController mvc) {
        super(mvc);
    }

    private static final long serialVersionUID = -2448342061117967555L;

    @Override
    public JPanel buildLeftSide() {
        return new JPanel();
    }
    
}

//Created table for SharedJourneys: will appear on SharedJourneys tab
public class SharedJourneysTableView extends BaseTableView {

    private static final long serialVersionUID = 1156877669628672936L;

    public SharedJourneysTableView(MainViewController mvc) {
        super(new SharedJourneysTopBar(mvc));
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
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" },
                { "new Integer(1)", "Shenzen", "Rotterdam", "tobacco", "13/03/2020 4:20", "13/04/2020 4:20" } };
        return data;
    }

}
