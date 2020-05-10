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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import rcm.model.Application;

public class EndJourneyView extends JDialog {
    private static final long serialVersionUID = -3640917496556492346L;

    private JTextField dateTimeField = new JTextField(12);
    private JLabel lbl1 = new JLabel("End Date:");

    private JRadioButton r1 = new JRadioButton("Now");
    private ClassLoader classLoader = getClass().getClassLoader();
    private ImageIcon image = new ImageIcon(classLoader.getResource("calendar_icon.png"));
    private JRadioButton r2 = new JRadioButton(image);
    private JButton b1 = new JButton("End");
    private JButton b2 = new JButton("Cancel");

    public EndJourneyView(Application app, int journey_id) {

        setTitle("End Journey");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(300, 200)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // StartDate
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        // Radio Buttons
        Box rBox = Box.createHorizontalBox();
        ButtonGroup rGroup = new ButtonGroup();
        r1.setActionCommand("Now");
        r2.setActionCommand("Choose");
        rGroup.add(r1);
        rGroup.add(r2);
        rBox.add(r1);
        rBox.add(r2);

        r1.addKeyListener(kl);
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date obj = new Date();
                dateTimeField.setText(sdf.format(obj));
            }
        });

        r2.addKeyListener(kl);
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTimeField.setText(new DatePickerView().setPickedDate());
            }
        });

        r1.doClick();

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(rBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        dateTimeField.setPreferredSize(new Dimension(230, 20));
        dateTimeField.addKeyListener(kl);
        panel.add(dateTimeField, constraints);

        // End Button
        constraints.gridx = 0;
        constraints.gridy = 2;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), formatter);
                    if (app.endJourney(journey_id, dateTime)) {
                        Dialog.InfoDialog("Your journey has successfully ended", "Successful ending");
                        app.fireChange("endJourney");
                        dispose();
                    } else {
                        Dialog.ErrorDialog("Failed to end the journey, check the time stamp", "Failed to end journey");
                    }
                } catch (DateTimeParseException e) {
                    Dialog.ErrorDialog("Please input date & time in the correct format", "Invalid date error");
                }
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
                } else {
                    b1.doClick();
                }
            }
        }
    }
}
