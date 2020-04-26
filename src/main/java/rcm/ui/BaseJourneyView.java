package rcm.ui;

import java.awt.Component;

import javax.swing.JPanel;

public abstract class BaseJourneyView extends BaseView {

    private static final long serialVersionUID = -7158594990405366048L;

    protected BaseJourneyView(BaseTopBar topBar) {
        super(topBar);
    }

    @Override
    protected Component buildContent() {
        JPanel panel = new JPanel();
        
        // TODO Auto-generated method stub
        return panel;
    }

    protected abstract void buildButtons();
    
}
