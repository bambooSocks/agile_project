package rcm.ui.journey;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;
import rcm.model.ContainerStatus;
import rcm.ui.BaseTopBar;
import rcm.ui.popup.EndJourneyView;
import rcm.ui.popup.EnterStatusView;
import rcm.ui.popup.StartJourneyView;

class CompanyJourneyTopBar extends BaseTopBar {

    private static final long serialVersionUID = 6993300655884720698L;

    public CompanyJourneyTopBar(Application app) {
        super(app, null, false);
    }

    @Override
    public JPanel buildLeftSide() {
        JPanel topSide = new JPanel(new BorderLayout());
        JButton backButton = new JButton(" < ");
        backButton.setFont(new Font("", Font.BOLD, 12));
        backButton.setPreferredSize(new Dimension(50, 30));
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                app.fireChange("dismissCompanyJourney");
            }
        });
        topSide.add(backButton, BorderLayout.WEST);
        return topSide;
    }
}

public class CompanyJourneyView extends BaseJourneyView implements PropertyChangeListener {

    private static final long serialVersionUID = -6993300655884720698L;
    private Integer client_id, container_id = null;

    private JButton enterStatus;
    private JButton startJourney;
    private JButton endJourney;

    public CompanyJourneyView(Application app) {
        super(app, new CompanyJourneyTopBar(app));
        app.addObserver(this);
    }

    @Override
    protected JPanel buildRightButton() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        enterStatus = new JButton("Enter Status");
        startJourney = new JButton("Start Journey");
        endJourney = new JButton("End Journey");
        enterStatus.setFont(new Font("", Font.PLAIN, 14));
        enterStatus.setPreferredSize(new Dimension(150, 30));
        enterStatus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EnterStatusView popup = new EnterStatusView(app, journey_id);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });

        startJourney.setFont(new Font("", Font.PLAIN, 14));
        startJourney.setPreferredSize(new Dimension(150, 30));
        if (journey != null && journey.isStarted()) {
            startJourney.setEnabled(false);
        } else {
            startJourney.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StartJourneyView popup = new StartJourneyView(app, journey_id);
                    popup.setLocationRelativeTo(null);
                    popup.setVisible(true);
                }
            });
        }

        endJourney.setFont(new Font("", Font.PLAIN, 14));
        endJourney.setPreferredSize(new Dimension(150, 30));
        if (journey != null && (!journey.isStarted() || journey.isEnded())) {
            endJourney.setEnabled(false);
        } else {
            endJourney.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    EndJourneyView popup = new EndJourneyView(app, journey_id);
                    popup.setLocationRelativeTo(null);
                    popup.setVisible(true);
                }
            });
        }

        rightPanel.add(enterStatus);
        rightPanel.add(startJourney);
        rightPanel.add(endJourney);
        rightPanel.setLayout(new GridLayout(3, 2, 10, 10));

        return rightPanel;
    }

    @Override
    protected List<ContainerStatus> requestStatus() {
        statuses = null;

        if (container_id == null && client_id != null) {
            statuses = app.getClientById(client_id).requestStatus(app.getJourneyById(journey_id));
        } else if (container_id != null && client_id == null) {
            statuses = app.getContainerById(container_id).requestStatus(app.getJourneyById(journey_id));
        }
        return statuses;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "newStatus":
        case "showCompanyJourney":
            journey = app.getJourneyById(journey_id);
            statuses = requestStatus();
            contentLabelsPanel.updatePanel();
            tempGraph.updateGraph(journey_id);
            pressureGraph.updateGraph(journey_id);
            humidityGraph.updateGraph(journey_id);
            updateLocationTable();
            break;
        case "setCompanyJourneysClient":
            client_id = (int) evt.getNewValue();
            container_id = null;
            break;
        case "setCompanyJourneysContainer":
            container_id = (int) evt.getNewValue();
            client_id = null;
            break;

        case "aPendingJourney":
            enterStatus.setEnabled(false);
            startJourney.setEnabled(true);
            endJourney.setEnabled(false);
            break;

        case "startJourney":
        case "aStartedJourney":
            journey = app.getJourneyById(journey_id);
            contentLabelsPanel.updatePanel();
            enterStatus.setEnabled(true);
            startJourney.setEnabled(false);
            endJourney.setEnabled(true);
            break;

        case "endJourney":
        case "anEndedJourney":
            journey = app.getJourneyById(journey_id);
            contentLabelsPanel.updatePanel();
            enterStatus.setEnabled(false);
            startJourney.setEnabled(false);
            endJourney.setEnabled(false);
            break;

        default:
            break;
        }
    }
}