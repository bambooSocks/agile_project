package rcm.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rcm.model.Application;
import rcm.ui.journey.MyJourneyView;
import rcm.ui.journey.SharedJourneyView;
import rcm.ui.tables.MyJourneysTableView;
import rcm.ui.tables.SharedJourneysTableView;

public class ClientTabView extends JTabbedPane implements PropertyChangeListener {
    final static String TABLEPANEL = "Table Panel";
    final static String JOURNEYPANEL = "Journey Panel";
    final static String SHAREDJOURNEYPANEL = "Shared Journey Panel";
    private Application app;
    private MyJourneyView journeyView;
    private MyJourneysTableView journeysTableView;
    private SharedJourneyView sharedJourneyView;
    private SharedJourneysTableView sharedJourneysTableView;

    private static final long serialVersionUID = 4767770251924620192L;

    public ClientTabView(Application app) {
        this.app = app;
        app.addObserver(this);
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                app.clientTabChanged();
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
        sharedJourneyCards.add(sharedJourneysTableView, TABLEPANEL);
        sharedJourneyCards.add(sharedJourneyView, SHAREDJOURNEYPANEL);
        return sharedJourneyCards;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "showJourney":
            journeyView.setJourneyID((int) evt.getNewValue());
            journeyView.setVisible(true);
            journeysTableView.setVisible(false);
            break;

        case "MyJourneyTable":
            journeyView.setVisible(false);
            journeysTableView.setVisible(true);
            break;
            
        case "SharedJourneyTable":
            sharedJourneyView.setVisible(false);
            sharedJourneysTableView.setVisible(true);
            break;
            
        case "showSharedJourney":
            sharedJourneyView.setVisible(true);
            sharedJourneysTableView.setVisible(false);
            break;
        default:
            break;
        }

    }

}
