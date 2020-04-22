package rcm;

import javax.swing.JFrame;
import rcm.ui.ClientsTableView;

public class Application {

    public static void main(String[] args) {

        // TODO: change this for actual code
        JFrame f = new JFrame("Clients view");

        ClientsTableView bar = new ClientsTableView();

        f.add(bar);

        f.pack();
        f.setVisible(true);
    }

}
