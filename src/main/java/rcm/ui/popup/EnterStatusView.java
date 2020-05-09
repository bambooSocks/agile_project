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

public class EnterStatusView extends JDialog {
    private static final long serialVersionUID = 905381235747598544L;

    private JTextField tempField = new JTextField(12);
    private JTextField humidityField = new JTextField(12);
    private JTextField atmPressureField = new JTextField(12);
    private JTextField locationField = new JTextField(12);
    private JTextField dateTimeField = new JTextField(12);

    private JLabel lbl1 = new JLabel("Temperature:");
    private JLabel lbl2 = new JLabel("Humidity:");
    private JLabel lbl3 = new JLabel("Atmospheric Pressure:");
    private JLabel lbl4 = new JLabel("Location:");
    private JLabel lbl5 = new JLabel("Date & Time:");

    private JRadioButton r1 = new JRadioButton("Now");
    private ImageIcon image = new ImageIcon("src/main/resources/calendar_icon.png");
    private JRadioButton r2 = new JRadioButton(image);
    private JButton b1 = new JButton("Enter");
    private JButton b2 = new JButton("Cancel");

    public EnterStatusView(Application app, int journey_id) {

        setTitle("Enter Status");
        setModal(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 400)); // (width, height)
        KeyListener kl = new KeyListener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Temperature
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lbl1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        tempField.addKeyListener(kl);
        panel.add(tempField, constraints);

        // Humidity
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbl2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        humidityField.addKeyListener(kl);
        panel.add(humidityField, constraints);

        // Atmospheric Pressure
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lbl3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        atmPressureField.addKeyListener(kl);
        panel.add(atmPressureField, constraints);

        // Location
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(lbl4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        locationField.addKeyListener(kl);
        panel.add(locationField, constraints);

        // Date & Time
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lbl5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        dateTimeField.addKeyListener(kl);
        panel.add(dateTimeField, constraints);

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
        constraints.gridy = 4;
        panel.add(rBox, constraints);

        // Enter Button
        constraints.gridx = 0;
        constraints.gridy = 6;
        b1.addKeyListener(kl);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String error = "Please input:";
                if (tempField.getText().isEmpty()) {
                    error += " temperature";
                }
                if (humidityField.getText().isEmpty()) {
                    error += " humidity";
                }
                if (atmPressureField.getText().isEmpty()) {
                    error += " pressure";
                }
                if (dateTimeField.getText().isEmpty()) {
                    error += " date";
                }
                if (locationField.getText().isEmpty()) {
                    error += " location";
                }
                if (error.length() > "Please input:".length()) {
                    Dialog.ErrorDialog(error, "Empty field error");
                } else {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), formatter);
                        if (app.enterNewContainerStatus(journey_id, dateTime, Double.parseDouble(tempField.getText()),
                                Double.parseDouble(humidityField.getText()),
                                Double.parseDouble(atmPressureField.getText()), locationField.getText())) {
                            Dialog.InfoDialog("The status has been added to the journey", "Successful status");
                            app.fireChange("newStatus");
                        } else {
                            Dialog.ErrorDialog("The status was not added to the journey", "Failed to add status");
                        }
                        dispose();
                    } catch (NumberFormatException e) {
                        Dialog.ErrorDialog("Temperature, humidity, and pressure must be a number.",
                                "Number Format error");
                    } catch (IOException e) {
                        Dialog.ErrorDialog("Something went wrong with the database", "Database error");
                    } catch (DateTimeParseException e) {
                        Dialog.ErrorDialog("Please input date & time in the correct format", "Invalid date error");
                    }
                }
            }
        });
        panel.add(b1, constraints);

        // Cancel Button
        constraints.gridx = 1;
        constraints.gridy = 6;
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
