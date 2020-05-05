package rcm.ui.info;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rcm.model.Application;
import rcm.ui.BaseTopBar;
import rcm.ui.BaseView;

public abstract class BaseInfoView extends BaseView implements PropertyChangeListener{

    private static final long serialVersionUID = 3559026551108682625L;
    protected JTable table;
    
    protected BaseInfoView(Application app, BaseTopBar topBar) {
        super(app, topBar);
        app.addObserver(this);
    }

    @Override
    protected Component buildContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        table = new JTable();

        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);

//        table.addMouseListener(new TableMouseListeners(table));

        panel.add(buildInfoPanel());
        panel.add(table);
        
        return new JScrollPane(panel);
    }
    
    public abstract void updateTableModel();
    
    public abstract JPanel buildInfoPanel();
    
}
