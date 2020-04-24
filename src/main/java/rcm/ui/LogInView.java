package rcm.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rcm.Application;

public class LogInView extends JPanel {

    private static final long serialVersionUID = -525974348663829648L;

    private JTextField emailField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JLabel lbl1 = new JLabel("Email:");
    private JLabel lbl2 = new JLabel("Password:");
    private JButton b1 = new JButton("Login");

    public LogInView(Application app) {

        JPanel panel = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(400, 200)); // (width, height)

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        // Password
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Login Button
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.loginUser(emailField.getText(), passwordField.getPassword());
            }
        });
        panel.add(b1, constraints);

//         set border for the panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel"));

        add(panel);
    }
}
