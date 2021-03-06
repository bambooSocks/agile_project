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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.*;

public class TimePickerView extends JDialog {

    private static final long serialVersionUID = 9206753105430847149L;

    private JTextField timeField = new JTextField(10);
    private JLabel lbl1 = new JLabel("Time:");
    private ClassLoader classLoader = getClass().getClassLoader();
    private ImageIcon nowIcon = new ImageIcon(classLoader.getResource("now_icon.jpg"));
    private JButton b1 = new JButton(nowIcon);
    private JButton b2 = new JButton("Enter");
    private JButton b3 = new JButton("Cancel");
    private LocalTime time;

    public TimePickerView() {

        setTitle("Time Picker");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(375, 150)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        timeField.setHorizontalAlignment(SwingConstants.RIGHT);
        timeField.addKeyListener(kl);
        panel.add(timeField, constraints);

        // Now button
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date obj = new Date();
                timeField.setText(sdf.format(obj));
            }
        });

        b1.setMargin(new Insets(0, 0, 0, 0));
        b1.setBounds(0, 0, 19, 19);
        timeField.add(b1);

        // Enter Button
        constraints.gridx = 0;
        constraints.gridy = 1;
        b2.addKeyListener(kl);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    time = LocalTime.parse(timeField.getText(), formatter);
                    dispose();
                } catch (DateTimeParseException e) {
                    Dialog.ErrorDialog("Please input time in the correct format", "Invalid time error");
                }
            }
        });
        panel.add(b2, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 1;
        b3.addKeyListener(kl);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(b3, constraints);

        panel.setBorder(BorderFactory
                .createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                        BorderFactory.createRaisedBevelBorder()), BorderFactory.createLoweredBevelBorder()));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getTime() {
        try {
            return time.toString();
        } catch (NullPointerException e) {
        }
        return "";
    }

    class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                if (event.getSource() == b3) {
                    b3.doClick();
                } else {
                    b2.doClick();
                }
            }
        }
    }
}
