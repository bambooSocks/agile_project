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

public class CreateClientView extends JFrame {
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
        super("Create Client View");

        JPanel panel = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(400, 350)); // (width, height)       // probably need to adjust this 

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
        
        // New Password
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lbl5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(passwordField1, constraints);
        
        // Confirm Password
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(lbl6, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(passwordField2, constraints);

        // Create Button
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create clicked");
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
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
