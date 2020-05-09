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
import javax.swing.JTextField;

import rcm.model.Application;

public class JourneyShareView extends JDialog {

    private static final long serialVersionUID = -6276216260193469897L;

    private JTextField client = new JTextField();

    private JLabel lbl1 = new JLabel("Please enter a client email to share with:");

    private JButton b1 = new JButton("OK");
    private JButton b2 = new JButton("Cancel");

    public JourneyShareView(Application app, int journey_id) {

        setTitle("Share Journey");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(375, 150)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Share TextField
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(lbl1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        client.setPreferredSize(new Dimension(230, 20));
        client.addKeyListener(kl);
        panel.add(client, constraints);

        // OK Button
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Integer id = app.searchClientIdByEmail(client.getText());
                if (id != null) {
                    app.shareJourney(id, journey_id);
                    Dialog.InfoDialog("Journey has been successfully shared", "Successful sharing");
                } else {
                    Dialog.ErrorDialog("don't have that email sorry", "Input error");
                }
                dispose();
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 2;
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
