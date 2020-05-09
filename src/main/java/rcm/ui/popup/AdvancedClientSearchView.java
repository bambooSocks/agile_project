package rcm.ui.popup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rcm.model.Application;

public class AdvancedClientSearchView extends BaseAdvancedSearchView {

    private static final long serialVersionUID = -7175406337438643480L;

    private JTextField search = new JTextField(15);

    private JLabel lbl1 = new JLabel("Search:");
    private JLabel lbl2 = new JLabel("Filter by:");

    private JCheckBox cb1 = new JCheckBox("Name");
    private JCheckBox cb2 = new JCheckBox("Address");
    private JCheckBox cb3 = new JCheckBox("Reference Person");
    private JCheckBox cb4 = new JCheckBox("Email");

    private JButton b1 = new JButton("Search");
    private JButton b2 = new JButton("Cancel");

    public AdvancedClientSearchView(Application app) {

        setTitle("Advanced Client Search");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Search TextField
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        search.addKeyListener(kl);
        panel.add(search, constraints);

        // Checkboxes
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        panel.add(lbl2, constraints);

        Box cbBox = Box.createVerticalBox();
        cbBox.add(cb1);
        cbBox.add(cb2);
        cbBox.add(cb3);
        cbBox.add(cb4);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 1;
        cb1.addKeyListener(kl);
        cb2.addKeyListener(kl);
        cb3.addKeyListener(kl);
        cb4.addKeyListener(kl);
        panel.add(cbBox, constraints);

        // Search Button
        constraints.gridx = 0;
        constraints.gridy = 2;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Object> filters = new HashMap<>();
                filters.put("query", search.getText());
                filters.put("name", cb1.isSelected());
                filters.put("address", cb2.isSelected());
                filters.put("refPerson", cb3.isSelected());
                filters.put("email", cb4.isSelected());
                app.fireChange(cmd, filters);
                dispose();
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 2;
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
                } else if (event.getSource() == cb1) {
                    cb1.doClick();
                } else if (event.getSource() == cb2) {
                    cb2.doClick();
                } else if (event.getSource() == cb3) {
                    cb3.doClick();
                } else if (event.getSource() == cb4) {
                    cb4.doClick();
                } else {
                    b1.doClick();
                }
            }
        }
    }
}
