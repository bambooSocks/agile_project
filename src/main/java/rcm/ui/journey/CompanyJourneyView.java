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
    private Integer client_id = -1, container_id = -1;

    public CompanyJourneyView(Application app) {
        super(app, new CompanyJourneyTopBar(app));
        app.addObserver(this);
    }

    @Override
    protected JPanel buildRightButton() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // TODO: Switch to button?
        JButton enterStatus = new JButton("Enter Status");
        enterStatus.setFont(new Font("", Font.PLAIN, 14));
        enterStatus.setPreferredSize(new Dimension(150, 30));
        enterStatus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EnterStatusView popup = new EnterStatusView(app, journey_id);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });

        JButton startJourney = new JButton("Start Journey");
        startJourney.setFont(new Font("", Font.PLAIN, 14));
        startJourney.setPreferredSize(new Dimension(150, 30));
        startJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StartJourneyView popup = new StartJourneyView(app, journey_id);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });

        JButton endJourney = new JButton("End Journey");
        endJourney.setFont(new Font("", Font.PLAIN, 14));
        endJourney.setPreferredSize(new Dimension(150, 30));
        endJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EndJourneyView popup = new EndJourneyView(app, journey_id);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });

        rightPanel.add(enterStatus);
        rightPanel.add(startJourney);
        rightPanel.add(endJourney);
        rightPanel.setLayout(new GridLayout(3, 2, 10, 10));

        return rightPanel;
    }

    @Override
    protected List<ContainerStatus> requestStatus() {
        List<ContainerStatus> statuses = null;

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
        case "startJourney":
        case "endJourney":
        case "showCompanyJourney":
            journey = app.getJourneyById(journey_id);
            contentLabelsPanel.updatePanel();
            if (tempGraph != null) tempGraph.updateGraph(journey_id);
            if (pressureGraph != null) pressureGraph.updateGraph(journey_id);
            if (humidityGraph != null) humidityGraph.updateGraph(journey_id);
            if (locationTable != null) updateLocationTable(locationTable, journey_id);
            break;
        case "setCompanyJourneysClient":
            client_id = (int) evt.getNewValue();
            container_id = null;
            break;
        case "setCompanyJourneysContainer":
            container_id = (int) evt.getNewValue();
            client_id = null;
            break;
        default:
            break;
        }
    }
}