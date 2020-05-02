package rcm.ui.popup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnterStatusView extends JDialog {

    private static final long serialVersionUID = 905381235747598544L;

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

    private ImageIcon nowIcon = new ImageIcon("src/main/resources/now_icon.jpg");
    private JButton b1 = new JButton(nowIcon);
    private JButton b2 = new JButton("Enter");
    private JButton b3 = new JButton("Cancel");

    public EnterStatusView() {

        setTitle("Enter Status");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300)); // (width, height)

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 30, 10, 30);

        // Temperature
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        tempField.addKeyListener(new KeyListener());
        panel.add(tempField, constraints);

        // Humidity
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        humidityField.addKeyListener(new KeyListener());
        panel.add(humidityField, constraints);

        // Atmospheric Pressure
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        atmPressureField.addKeyListener(new KeyListener());
        panel.add(atmPressureField, constraints);

        // Location
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        locationField.addKeyListener(new KeyListener());
        panel.add(locationField, constraints);

        // Time
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lbl5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        timeField.addKeyListener(new KeyListener());
        panel.add(timeField, constraints);

        // Now button
        b1.addKeyListener(new KeyListener());
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Now clicked");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date obj = new Date();
                timeField.setText(sdf.format(obj));
            }
        });

        b1.setMargin(new Insets(0, 0, 0, 0));
        b1.setBounds(74, 0, 19, 19);
        timeField.add(b1);

        // Enter Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        b2.addKeyListener(new KeyListener());
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter clicked");
                // TODO: save entered data
                dispose();
            }
        });
        panel.add(b2, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 5;
        b3.addKeyListener(new KeyListener());
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel clicked");
                dispose();
            }
        });
        panel.add(b3, constraints);

        // set border for the panel
        panel.setBorder(BorderFactory
                .createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                        BorderFactory.createRaisedBevelBorder()), BorderFactory.createLoweredBevelBorder()));

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                if (event.getSource() == b1) {
                    b1.doClick();
                } else if (event.getSource() == b3) {
                    b3.doClick();
                } else {
                    b2.doClick();
                }
            }
        }
    }
}
