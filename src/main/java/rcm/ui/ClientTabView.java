package rcm.ui;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rcm.model.Application;
import rcm.ui.journey.BaseJourneyView;
import rcm.ui.journey.MyJourneyView;
import rcm.ui.journey.SharedJourneyView;
import rcm.ui.tables.BaseTableView;
import rcm.ui.tables.MyJourneysTableView;
import rcm.ui.tables.SharedJourneysTableView;

public class ClientTabView extends JTabbedPane implements PropertyChangeListener {
    final static String TABLEPANEL = "Table Panel";
    final static String SHAREDTABLEPANEL = "Shared Table Panel";
    final static String JOURNEYPANEL = "Journey Panel";
    final static String SHAREDJOURNEYPANEL = "Shared Journey Panel";
    private Application app;
    private BaseJourneyView journeyView;
    private BaseTableView journeysTableView;
    private BaseJourneyView sharedJourneyView;
    private BaseTableView sharedJourneysTableView;

    private static final long serialVersionUID = 4767770251924620192L;

    public ClientTabView(Application app) {
        this.app = app;
        app.addObserver(this);
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                app.fireChange("clientTabChanged", getSelectedIndex());
            }
        });

        addTab("My Journeys", createMyJourneyCards());
        setMnemonicAt(0, KeyEvent.VK_1);
        addTab("Shared Journeys", createSharedJourneyCards());
        setMnemonicAt(1, KeyEvent.VK_2);
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private JPanel createMyJourneyCards() {
        JPanel myJourneyCards = new JPanel();
        myJourneyCards.setLayout(new CardLayout());
        journeyView = new MyJourneyView(app);
        journeysTableView = new MyJourneysTableView(app);
        myJourneyCards.add(journeysTableView, TABLEPANEL);
        myJourneyCards.add(journeyView, JOURNEYPANEL);
        return myJourneyCards;
    }

    private JPanel createSharedJourneyCards() {
        JPanel sharedJourneyCards = new JPanel();
        sharedJourneyCards.setLayout(new CardLayout());
        sharedJourneyView = new SharedJourneyView(app);
        sharedJourneysTableView = new SharedJourneysTableView(app);
        sharedJourneyCards.add(sharedJourneysTableView, SHAREDTABLEPANEL);
        sharedJourneyCards.add(sharedJourneyView, SHAREDJOURNEYPANEL);
        return sharedJourneyCards;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "clientLoggedIn":
        case "showMyJourneyTable":
            setSelectedIndex(0);
            journeyView.setVisible(false);
            journeysTableView.setVisible(true);
            break;
        case "showSharedJourneyTable":
            setSelectedIndex(1);
            sharedJourneyView.setVisible(false);
            sharedJourneysTableView.setVisible(true);
            break;
        case "showJourney":
            journeyView.setJourneyID((int) evt.getNewValue());
            journeyView.setVisible(true);
            journeysTableView.setVisible(false);
            break;
        case "showSharedJourney":
            sharedJourneyView.setJourneyID((int) evt.getNewValue());
            sharedJourneyView.setVisible(true);
            sharedJourneysTableView.setVisible(false);
            break;
        default:
            break;
        }
    }
}
