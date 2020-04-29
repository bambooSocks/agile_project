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

public class CompanyJourneyView extends BaseJourneyView {

    private static final long serialVersionUID = -6993300655884720698L;

    public CompanyJourneyView(Application app) {
        super(app, new JourneyTopBar(app));
    }

    @Override
    protected JPanel buildRightButton() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // TODO: Switch to button?
        JButton enterStatus = new JButton("Enter Status");
        enterStatus.setFont(new Font("Serif", Font.BOLD, 14));
        enterStatus.setPreferredSize(new Dimension(150, 30));
        enterStatus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter Status clicked");
            }
        });
        
        JButton startJourney = new JButton("Start Journey");
        startJourney.setFont(new Font("Serif", Font.PLAIN, 14));
        startJourney.setPreferredSize(new Dimension(150, 30));
        startJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Start journey clicked");
            }
        });
        
        JButton endJourney = new JButton("End Journey");
        endJourney.setFont(new Font("Serif", Font.PLAIN, 14));
        endJourney.setPreferredSize(new Dimension(150, 30));
        endJourney.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("End journey clicked");
            }
        });
        
        rightPanel.add(enterStatus);
        rightPanel.add(startJourney);
        rightPanel.add(endJourney);
        rightPanel.setLayout(new GridLayout(3, 2, 10, 10));

        return rightPanel;
    }

}