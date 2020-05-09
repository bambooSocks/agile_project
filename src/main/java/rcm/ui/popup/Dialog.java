package rcm.ui.popup;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog {

    /**
     * Opens an error dialog
     * 
     * @param msg   Message to be shown
     * @param title Title of the dialog window
     */
    public static void ErrorDialog(String msg, String title) {
        JOptionPane.showMessageDialog(new JFrame(), msg, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Opens a warning dialog
     * 
     * @param msg   Message to be shown
     * @param title Title of the dialog window
     */
    public static void WarningDialog(String msg, String title) {
        JOptionPane.showMessageDialog(new JFrame(), msg, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Opens an information dialog
     * 
     * @param msg   Message to be shown
     * @param title Title of the dialog window
     */
    public static void InfoDialog(String msg, String title) {
        JOptionPane.showMessageDialog(new JFrame(), msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
