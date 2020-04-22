package rcm.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BaseTableView extends JPanel {

    private static final long serialVersionUID = 1L;

    public BaseTableView() {
        super(new GridLayout(1, 0));

        final JTable table = new JTable(addData(), addcolumnNames());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        // TODO: impelement double click and right click event according to each table
        // (make an abstract method here)
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Select row");
            }
        });

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);
    }

    public abstract String[] addcolumnNames();

    public abstract Object[][] addData();
}