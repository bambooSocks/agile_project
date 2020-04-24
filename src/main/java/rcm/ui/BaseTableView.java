package rcm.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BaseTableView extends JPanel {

    private static final long serialVersionUID = -9046917894422843234L;

    public BaseTableView(BaseTopBar topBar) {
        
        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
        
        final JTable table = new JTable(addData(), addColumnNames());
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
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
        add(scrollPane, BorderLayout.CENTER);
    }

    public abstract String[] addColumnNames();

    public abstract Object[][] addData();
}