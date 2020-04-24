package rcm.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreateJourneyView extends JFrame {
    private static final long serialVersionUID = 1242136189691358210L;

    private JTextField originField = new JTextField(10);
    private JTextField destinationField = new JTextField(10);
    private JTextField contentField = new JTextField(10);

    private JLabel lbl1 = new JLabel("Origin:");
    private JLabel lbl2 = new JLabel("Destination:");
    private JLabel lbl3 = new JLabel("Content:");
    private JLabel lbl4 = new JLabel("Start Date:");

    private JRadioButton r1 = new JRadioButton("Now");
    private JRadioButton r2 = new JRadioButton("Choose Date");

    private JButton b1 = new JButton("Request");
    private JButton b2 = new JButton("Cancel");

    public CreateJourneyView() {
        super("Create Journey View");

        JPanel panel = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(400, 350)); // (width, height) // probably need to adjust this

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Origin
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(originField, constraints);

        // Destination
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(destinationField, constraints);

        // Content
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(contentField, constraints);

        // StartDate
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        // Radio Buttons
        Box rBox = Box.createVerticalBox();
        ButtonGroup rGroup = new ButtonGroup();
        r1.isSelected();
        r1.setMnemonic(KeyEvent.VK_B);
        r1.setActionCommand("Now");
        r2.setMnemonic(KeyEvent.VK_B);
        r2.setActionCommand("Choose Date");
        rGroup.add(r1);
        rGroup.add(r2);
        rBox.add(r1);
        rBox.add(r2);
        
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Now clicked");
                // need to input todays date here
            }
        });
        
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Date clicked");
                // need to add the date selector here
            }
        });
        
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(rBox, constraints);

        // Request Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Request clicked");
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 5;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel clicked");
                dispose();
            }
        });
        panel.add(b2, constraints);

//         set border for the panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " "));

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

}
