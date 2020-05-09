package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import rcm.model.Application;

public abstract class BaseView extends JPanel {

    private static final long serialVersionUID = -4680002105984746836L;
    protected Application app;

    protected BaseView(Application app, BaseTopBar topBar) {

        setLayout(new BorderLayout());
        this.app = app;

        add(topBar, BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 550);
    }

    protected abstract Component buildContent();
}
