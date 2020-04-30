package rcm.ui.tables;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import rcm.model.Application;
import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

public abstract class BaseTableView extends BaseView implements PropertyChangeListener {

    private static final long serialVersionUID = -9046917894422843234L;
    protected JTable table;
    
    public BaseTableView(Application app, BaseTopBar topBar) {
        super(app, topBar);
        app.addObserver(this);
    }

    @Override
    protected Component buildContent() {
        table = new JTable();
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);

        // TODO: implement double click and right click event according to each table
        // (make an abstract method here)
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Select row");
            }
        });
        
        // Create the scroll pane and add the table to it.
        return new JScrollPane(table);
    }
    
    public abstract void updateTableModel();

}