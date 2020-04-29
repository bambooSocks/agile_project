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
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreateJourneyView extends JFrame {
    private static final long serialVersionUID = 1242136189691358210L;

    private JTextField originField = new JTextField(10);
    private JTextField destinationField = new JTextField(10);
    private JTextField contentField = new JTextField(10);

    private JLabel lbl1 = new JLabel("Origin:");
    private JLabel lbl2 = new JLabel("Destination:");
    private JLabel lbl3 = new JLabel("Content:");
    private JLabel lbl4 = new JLabel("Start Date:");

    private JRadioButton r1 = new JRadioButton("Now");
    private JRadioButton r2 = new JRadioButton("Choose Date");

    private JButton b1 = new JButton("Request");
    private JButton b2 = new JButton("Cancel");

    public CreateJourneyView() {
        super("Create Journey View");

        JPanel panel = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(400, 350)); // (width, height) // probably need to adjust this

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Origin
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        originField.addKeyListener(new KeyListener());
        panel.add(originField, constraints);

        // Destination
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        destinationField.addKeyListener(new KeyListener());
        panel.add(destinationField, constraints);

        // Content
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        contentField.addKeyListener(new KeyListener());
        panel.add(contentField, constraints);

        // StartDate
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        // Radio Buttons
        Box rBox = Box.createVerticalBox();
        ButtonGroup rGroup = new ButtonGroup();
        final JTextField text = new JTextField(20);
        r1.isSelected();
//        r1.setMnemonic(KeyEvent.VK_B);
        r1.setActionCommand("Now");
//        r2.setMnemonic(KeyEvent.VK_B);
        r2.setActionCommand("Choose Date");
        rGroup.add(r1);
        rGroup.add(r2);
        rBox.add(r1);
        rBox.add(r2);
        rBox.add(text);

        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date obj = new Date();
                text.setText(sdf.format(obj));
                // TODO: need to add to the field
//                System.out.println(sdf.format(obj));
            }
        });

        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: working here
                final JFrame f = new JFrame();
                text.setText(new DatePickerView(f).setPickedDate());
                f.pack();
                f.setVisible(true);
            }
        });

        constraints.gridx = 1;
        constraints.gridy = 3;
        rBox.addKeyListener(new KeyListener());
        panel.add(rBox, constraints);

        // Request Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        b1.addKeyListener(new KeyListener());
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Request clicked");
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 5;
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
