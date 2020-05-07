package rcm.ui.journey;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;
import rcm.ui.BaseTopBar;
import rcm.ui.popup.JourneyShareView;

class MyJourneyTopBar extends BaseTopBar {

    private static final long serialVersionUID = 1L;

    public MyJourneyTopBar(Application app) {
        super(app, false);

    }

    @Override
    public JPanel buildLeftSide() {
        JPanel topSide = new JPanel(new BorderLayout());
        JButton backButton = new JButton(" < ");
        backButton.setFont(new Font("Serif", Font.BOLD, 12));
        backButton.setPreferredSize(new Dimension(50, 30));
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                app.switchCards("MyJourneyTable");
            }
        });
        topSide.add(backButton, BorderLayout.WEST);
        return topSide;
    }

}

public class MyJourneyView extends BaseJourneyView implements PropertyChangeListener {

    private static final long serialVersionUID = -6993300655884720698L;

    public MyJourneyView(Application app) {
        super(app, new MyJourneyTopBar(app));
        app.addObserver(this);
    }

    @Override
    protected JPanel buildRightButton() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        JButton shareJourney = new JButton("Share Journey");
        shareJourney.setFont(new Font("Serif", Font.PLAIN, 14));
        shareJourney.setPreferredSize(new Dimension(150, 30));
        shareJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Shared journey clicked");
                JourneyShareView popup = new JourneyShareView();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        rightPanel.add(shareJourney);

        return rightPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "showJourney":
            j = app.getJourneyById(journeyID);
            dateLabelsPanel.updatePanel();
            contentLabelsPanel.updatePanel();
            tempGraph.updateGraph(journeyID);
            pressureGraph.updateGraph(journeyID);
            humidityGraph.updateGraph(journeyID);
            break;

        default:
            break;
        }

    }

}
