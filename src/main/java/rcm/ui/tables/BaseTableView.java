package rcm.ui.tables;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import rcm.model.Application;

import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

class TableMouseListeners extends MouseAdapter {

    private JTable table;

    public TableMouseListeners(JTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        // TODO: Teo add try catch or something... it throws an exception
        table.setRowSelectionInterval(currentRow, currentRow);
    }
}

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

        table.addMouseListener(new TableMouseListeners(table));

        return new JScrollPane(table);
    }

    public abstract void updateTableModel();

}