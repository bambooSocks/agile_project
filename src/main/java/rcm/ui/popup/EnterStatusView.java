package rcm.ui.popup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rcm.ui.popup.ProfileInfoView.KeyListener;

public class EnterStatusView extends JFrame {

//  add this to each field/button:
//  "field name".addKeyListener(new KeyListener());
    private static final long serialVersionUID = 120614387649860592L;

    private JTextField tempField = new JTextField(10);
    private JTextField humidityField = new JTextField(10);
    private JTextField atmPressureField = new JTextField(10);
    private JTextField locationField = new JTextField(10);
    private JTextField timeField = new JTextField(10);

    private JLabel lbl1 = new JLabel("Temperature:");
    private JLabel lbl2 = new JLabel("Humidity:");
    private JLabel lbl3 = new JLabel("Atmospheric Pressure:");
    private JLabel lbl4 = new JLabel("Location:");
    private JLabel lbl5 = new JLabel("Time:");

    private JButton b1 = new JButton("Enter");
    private JButton b2 = new JButton("Cancel");

    public EnterStatusView() {

        setLayout(new GridBagLayout());
        setSize(new Dimension(400, 300)); // (width, height)

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 30, 10, 30);

        // Temperature
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        tempField.addKeyListener(new KeyListener());
        add(tempField, constraints);

        // Humidity
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        humidityField.addKeyListener(new KeyListener());
        add(humidityField, constraints);

        // Atmospheric Pressure
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        atmPressureField.addKeyListener(new KeyListener());
        add(atmPressureField, constraints);

        // Location
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        locationField.addKeyListener(new KeyListener());
        add(locationField, constraints);

        // Time
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(lbl5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        timeField.addKeyListener(new KeyListener());
        add(timeField, constraints);

        // Enter Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        b1.addKeyListener(new KeyListener());
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter clicked");
                // TODO: save entered data
                setVisible(false);
            }
        });
        add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        b2.addKeyListener(new KeyListener());
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel clicked");
                setVisible(false);
            }
        });
        add(b2, constraints);
    }

    // set border for the panel
//        setBorder(BorderFactory
//                .createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
//                 BorderFactory.createRaisedBevelBorder()), BorderFactory.createLoweredBevelBorder()));
//}

    class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
//      if (event.getSource() == b2) {
//          b2.doClick();
//      } else {
//      b1.doClick();
        }
    }
}
