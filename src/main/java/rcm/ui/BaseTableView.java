package rcm.ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BaseTableView extends JPanel {

    private static final long serialVersionUID = -336356677123673731L;
    
    public BaseTableView(BaseTopBar topBar) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(topBar);
        
        TableModel dataModel = new AbstractTableModel() {
            private static final long serialVersionUID = -8136696428302459484L;
            public int getColumnCount() { return 10; }
            public int getRowCount() { return 10;}
            public Object getValueAt(int row, int col) { return new Integer(row*col); }
        };
        
        JTable table = new JTable(dataModel);
        
        add(table);
        
    }
      
}
