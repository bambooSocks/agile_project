package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import rcm.model.Application;

public class JourneyTopBar extends BaseTopBar {

    public JourneyTopBar(Application app) {
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
                System.out.println("Go Back clicked"); 
            } 
        }); 
        topSide.add(backButton, BorderLayout.WEST); 
        return topSide; 
    }

}
