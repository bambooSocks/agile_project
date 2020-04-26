package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

public abstract class BaseView extends JPanel {

    private static final long serialVersionUID = -4680002105984746836L;

    protected BaseView(BaseTopBar topBar) {
        
        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
        
        add(buildContent(), BorderLayout.CENTER);
    }

    protected abstract Component buildContent();
    
}
