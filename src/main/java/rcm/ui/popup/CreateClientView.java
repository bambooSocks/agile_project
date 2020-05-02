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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateClientView extends JDialog {

    private static final long serialVersionUID = 1044408338796977511L;

    private JTextField nameField = new JTextField(10);
    private JTextField addressField = new JTextField(10);
    private JTextField refPersonField = new JTextField(10);
    private JTextField emailField = new JTextField(10);
    private JPasswordField passwordField1 = new JPasswordField(10);
    private JPasswordField passwordField2 = new JPasswordField(10);

    private JLabel lbl1 = new JLabel("Name:");
    private JLabel lbl2 = new JLabel("Address:");
    private JLabel lbl3 = new JLabel("Reference Person:");
    private JLabel lbl4 = new JLabel("Email:");
    private JLabel lbl5 = new JLabel("Password:");
    private JLabel lbl6 = new JLabel("Confirm Password:");

    private JButton b1 = new JButton("Create");
    private JButton b2 = new JButton("Cancel");

    public CreateClientView() {

        setTitle("Create New Client");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 350)); // (width, height) // probably need to adjust this

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Name
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        nameField.addKeyListener(new KeyListener());
        panel.add(nameField, constraints);

        // Address
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        addressField.addKeyListener(new KeyListener());
        panel.add(addressField, constraints);

        // Reference Person
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        refPersonField.addKeyListener(new KeyListener());
        panel.add(refPersonField, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        emailField.addKeyListener(new KeyListener());
        panel.add(emailField, constraints);

        // New Password
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lbl5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        passwordField1.addKeyListener(new KeyListener());
        panel.add(passwordField1, constraints);

        // Confirm Password
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(lbl6, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        passwordField2.addKeyListener(new KeyListener());
        panel.add(passwordField2, constraints);

        // Create Button
        constraints.gridx = 0;
        constraints.gridy = 6;
        b1.addKeyListener(new KeyListener());
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create clicked");
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 6;
        b2.addKeyListener(new KeyListener());
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(b2, constraints);

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
                if (event.getSource() == b2) {
                    b2.doClick();
                } else {
                    b1.doClick();
                }
            }
        }
    }
}
