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

import rcm.model.Application;

public class ProfileView extends JDialog {

    private static final long serialVersionUID = -4562833393035926979L;

    private JTextField nameField = new JTextField(10);
    private JTextField addressField = new JTextField(10);
    private JTextField refPersonField = new JTextField(10);
    private JTextField emailField = new JTextField(10);

    private JLabel lbl1 = new JLabel("Name:");
    private JLabel lbl2 = new JLabel("Address:");
    private JLabel lbl3 = new JLabel("Reference Person:");
    private JLabel lbl4 = new JLabel("Email:");

    private JButton b1 = new JButton("Change Password");
    private JButton b2 = new JButton("Save");
    private JButton b3 = new JButton("Cancel");

    public ProfileView(Application app) {

        setTitle("Manage Profile");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(new Dimension(400, 300)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Name
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        nameField.addKeyListener(kl);
        panel.add(nameField, constraints);

        // Address
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        addressField.addKeyListener(kl);
        panel.add(addressField, constraints);

        // Reference Person
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        refPersonField.addKeyListener(kl);
        panel.add(refPersonField, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        emailField.addKeyListener(kl);
        panel.add(emailField, constraints);

        // Change Password Button
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Password clicked");
                PasswordView pv = new PasswordView();
                pv.setVisible(true);
            }
        });
        panel.add(b1, constraints);

        // Save Button
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b2.addKeyListener(kl);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Save clicked");
                // TODO: update changed data

//                app.updateName(nameField.getText());
//                app.updateAddress(addressField.getText());
//                app.updateRefPerson(refPersonField.getText());
//                app.updateEmail(emailField.getText());
                dispose();
            }
        });
        panel.add(b2, constraints);

        // Cancel Button
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b3.addKeyListener(kl);
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

class PasswordView extends JDialog {

    private static final long serialVersionUID = -5106418056161602438L;

    private JLabel lbl1 = new JLabel("Old Password:");
    private JLabel lbl2 = new JLabel("New Password:");
    private JLabel lbl3 = new JLabel("Confirm Password:");

    private JPasswordField passwordField1 = new JPasswordField(10);
    private JPasswordField passwordField2 = new JPasswordField(10);
    private JPasswordField passwordField3 = new JPasswordField(10);

    private JButton b1 = new JButton("Save");
    private JButton b2 = new JButton("Cancel");

    public PasswordView() {

        setTitle("Change Password");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        KeyListener kl = new KeyListener();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Old Password
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        passwordField1.addKeyListener(kl);
        panel.add(passwordField1, constraints);

        // New Password
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField2.addKeyListener(kl);
        panel.add(passwordField2, constraints);

        // Confirm Password
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        passwordField3.addKeyListener(kl);
        panel.add(passwordField3, constraints);

        // Save Button
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save clicked");
                // TODO: update the passwords

//                check if passwordField1 is correct for that user
//                check if passwordField2 and passwordField3 match

//              app.updatePassword(new String(passwordField3.getPassword()));

                dispose();
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        b2.addKeyListener(kl);
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
