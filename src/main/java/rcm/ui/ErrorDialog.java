package rcm.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog extends JFrame {

    private static final long serialVersionUID = -745491829450283948L;

    public ErrorDialog(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title,
                JOptionPane.ERROR_MESSAGE);
    }
}
