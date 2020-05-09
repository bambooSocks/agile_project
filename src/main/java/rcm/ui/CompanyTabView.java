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
import rcm.ui.info.ClientsInfoView;
import rcm.ui.info.ContainersInfoView;
import rcm.ui.journey.BaseJourneyView;
import rcm.ui.journey.CompanyJourneyView;
import rcm.ui.tables.BaseTableView;
import rcm.ui.tables.ClientsTableView;
import rcm.ui.tables.ContainersTableView;

public class CompanyTabView extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = -465926656508535026L;
    private Application app;
    private BaseJourneyView journeyView;
    private JTabbedPane tabs;
    private BaseTableView clientsTableView;
    private ClientsInfoView clientsInfoView;
    private BaseTableView containersTableView;
    private ContainersInfoView containersInfoView;

    public CompanyTabView(Application app) {
        this.app = app;
        app.addObserver(this);
        setLayout(new CardLayout());
        tabs = new JTabbedPane();
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                app.fireChange("companyTabChanged", tabs.getSelectedIndex());
            }
        });
        tabs.addTab("Clients", createClientsCards());
        tabs.setMnemonicAt(0, KeyEvent.VK_1);
        tabs.addTab("Containers", createContainersCards());
        tabs.setMnemonicAt(1, KeyEvent.VK_2);

        journeyView = new CompanyJourneyView(app);

        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabs);
        add(journeyView);
    }

    private JPanel createClientsCards() {
        JPanel clientsCards = new JPanel();
        clientsCards.setLayout(new CardLayout());
        clientsTableView = new ClientsTableView(app);
        clientsInfoView = new ClientsInfoView(app);
        clientsCards.add(clientsTableView);
        clientsCards.add(clientsInfoView);
        return clientsCards;
    }

    private JPanel createContainersCards() {
        JPanel containersCards = new JPanel();
        containersCards.setLayout(new CardLayout());
        containersTableView = new ContainersTableView(app);
        containersInfoView = new ContainersInfoView(app);
        containersCards.add(containersTableView);
        containersCards.add(containersInfoView);
        return containersCards;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "showContainersTable":
            tabs.setVisible(true);
            tabs.setSelectedIndex(1);
            containersTableView.setVisible(true);
            containersInfoView.setVisible(false);
            journeyView.setVisible(false);
            break;
        case "showContainer":
            containersInfoView.setContainerID((int) evt.getNewValue());
            tabs.setVisible(true);
            containersTableView.setVisible(false);
            containersInfoView.setVisible(true);
            journeyView.setVisible(false);
            break;
        case "companyLoggedIn":
        case "showClientsTable":
            tabs.setVisible(true);
            tabs.setSelectedIndex(0);
            clientsTableView.setVisible(true);
            clientsInfoView.setVisible(false);
            journeyView.setVisible(false);
            break;
        case "showClient":
            clientsInfoView.setClientID((int) evt.getNewValue());
            tabs.setVisible(true);
            clientsTableView.setVisible(false);
            clientsInfoView.setVisible(true);
            journeyView.setVisible(false);
            break;
        case "showCompanyJourney":
            journeyView.setJourneyID((int) evt.getNewValue());
            journeyView.setVisible(true);
            tabs.setVisible(false);
            break;
        case "dismissCompanyJourney":
            journeyView.setVisible(false);
            tabs.setVisible(true);
            if (tabs.getSelectedIndex() == 0) {
                app.fireChange("updateClient");
            } else if (tabs.getSelectedIndex() == 1) {
                app.fireChange("updateContainer");
            }
        default:
            break;
        }
    }

}
