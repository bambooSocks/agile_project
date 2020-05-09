package rcm.ui.popup;

import javax.swing.JDialog;

public class BaseAdvancedSearchView extends JDialog {

    private static final long serialVersionUID = -7489523577028081253L;
    protected String cmd = "";

    public void setCommand(String cmd) {
        this.cmd = cmd;
    }
}
