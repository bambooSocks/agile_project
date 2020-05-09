package rcm.ui.popup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rcm.model.Application;

public class CreateJourneyView extends JDialog {
    private static final long serialVersionUID = 1242136189691358210L;

    private JTextField originField = new JTextField(12);
    private JTextField destinationField = new JTextField(12);
    private JTextField contentField = new JTextField(12);

    private JLabel lbl1 = new JLabel("Origin:");
    private JLabel lbl2 = new JLabel("Destination:");
    private JLabel lbl3 = new JLabel("Content:");

    private JButton b1 = new JButton("Request");
    private JButton b2 = new JButton("Cancel");

    public CreateJourneyView(Application app) {

        setTitle("Create New Journey");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(300, 350)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Origin
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        originField.addKeyListener(kl);
        panel.add(originField, constraints);

        // Destination
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        destinationField.addKeyListener(kl);
        panel.add(destinationField, constraints);

        // Content
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        contentField.addKeyListener(kl);
        panel.add(contentField, constraints);

        // Request Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String error = "Please input:";
                if (originField.getText().isEmpty()) {
                    error += " origin";
                }

                if (destinationField.getText().isEmpty()) {
                    error += " destination";
                }

                if (contentField.getText().isEmpty()) {
                    error += " content";
                }

                if (error.length() > "Please input:".length()) {
                    Dialog.ErrorDialog(error, "Empty field error");
                } else {
                    try {
                        app.requestNewJourney(originField.getText(), destinationField.getText(),
                                contentField.getText());
                        Dialog.InfoDialog("The journey has been created", "Successful journey creation");
                        app.fireChange("newJourney");
                        dispose();
                    } catch (IOException e) {
                        Dialog.ErrorDialog("Something went wrong with the database", "Database error");
                    }
                }
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 5;
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
