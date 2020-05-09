package rcm.ui.info;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rcm.model.Application;
import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;
import rcm.ui.UpdatablePanel;

class TableMouseListeners extends MouseAdapter {

    private JTable table;

    public TableMouseListeners(JTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        try {
            Point point = event.getPoint();
            int currentRow = table.rowAtPoint(point);
            table.setRowSelectionInterval(currentRow, currentRow);
        } catch (Exception e) {
        }
    }
}

public abstract class BaseInfoView extends BaseView implements PropertyChangeListener{

    private static final long serialVersionUID = 3559026551108682625L;
    protected JTable table;
    protected UpdatablePanel infoPanel;
    
    protected BaseInfoView(Application app, BaseTopBar topBar) {
        super(app, topBar);
        app.addObserver(this);
    }

    @Override
    protected Component buildContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        table = new JTable();

        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        table.addMouseListener(new TableMouseListeners(table));

        infoPanel = new UpdatablePanel() {
            private static final long serialVersionUID = 3559021108682655625L;

            @Override
            public JPanel buildContent() {
                return buildInfoPanel();
            }
        };
        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    public abstract void updateTableModel();
    
    public abstract JPanel buildInfoPanel();
    
}
