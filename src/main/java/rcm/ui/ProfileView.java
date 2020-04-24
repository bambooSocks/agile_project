package rcm.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ProfileView extends JFrame {
    private static final long serialVersionUID = -4562833393035926979L;

    private JTextField nameField = new JTextField(10);
    private JTextField addressField = new JTextField(10);
    private JTextField refPersonField = new JTextField(10);
    private JTextField emailField = new JTextField(10);
    private JPasswordField passwordField1 = new JPasswordField(10);
    private JPasswordField passwordField2 = new JPasswordField(10);
    private JPasswordField passwordField3 = new JPasswordField(10);

    private JLabel lbl1 = new JLabel("Name:");
    private JLabel lbl2 = new JLabel("Address:");
    private JLabel lbl3 = new JLabel("Reference Person:");
    private JLabel lbl4 = new JLabel("Email:");
    private JLabel lbl5 = new JLabel("Old Password:");
    private JLabel lbl6 = new JLabel("New Password:");
    private JLabel lbl7 = new JLabel("Confirm Password:");

    private JButton b1 = new JButton("Change Password");
    private JButton b2 = new JButton("Save");
    private JButton b3 = new JButton("Cancel");

    public ProfileView() {
        super("Profile View");

        JPanel panel = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(400, 350)); // (width, height)

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
        panel.add(nameField, constraints);

        // Address
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(addressField, constraints);

        // Reference Person
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(refPersonField, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(emailField, constraints);

        // Change Password Button
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Password clicked");
                new PasswordView();
                // having trouble with new components not appearing properly here...
                panel.removeAll();
                panel.updateUI();
                panel.revalidate();

            }
        });
        panel.add(b1, constraints);

        // Save Button
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save clicked");
            }
        });
        panel.add(b2, constraints);

        // Cancel Button
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel clicked");
                dispose();
            }
        });
        panel.add(b3, constraints);

//         set border for the panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " "));

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    class PasswordView {
        public PasswordView() {
            super();

            JPanel panel = new JPanel(new GridBagLayout());
            setPreferredSize(new Dimension(350, 350)); // (width, height)

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(10, 10, 10, 10);

            // Old Password
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(lbl5, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            panel.add(passwordField1, constraints);

            // New Password
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(lbl6, constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            panel.add(passwordField2, constraints);

            // Confirm Password
            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(lbl7, constraints);

            constraints.gridx = 1;
            constraints.gridy = 2;
            panel.add(passwordField3, constraints);

            // Save Button
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Save clicked");
                }
            });
            panel.add(b2, constraints);

            // Cancel Button
            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Cancel clicked");
                    new ProfileView();
                }
            });
            panel.add(b3, constraints);

//      set border for the panel
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " "));

            add(panel);
            pack();
//            setLocationRelativeTo(null);
        }
    }
}
