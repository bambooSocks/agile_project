package rcm.ui.journey;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;

public class ClientsJourneyView extends BaseJourneyView {

    private static final long serialVersionUID = -6993300655884720698L;

    public ClientsJourneyView(Application app) {
        super(app, new JourneyTopBar(app));
    }

    @Override
    protected JPanel buildRightButton() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // TODO: Switch to button?
        JButton shareJourney = new JButton("Share Journey");
        shareJourney.setFont(new Font("Serif", Font.PLAIN, 14));
        shareJourney.setPreferredSize(new Dimension(150, 30));
        shareJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Shared journey clicked");
            }
        });
        rightPanel.add(shareJourney);

        return rightPanel;
    }

}
