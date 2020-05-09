package rcm.ui;

import javax.swing.JPanel;

public abstract class UpdatablePanel extends JPanel {

    private JPanel content;

    private static final long serialVersionUID = -4030329736790627164L;

    public UpdatablePanel() {
        content = new JPanel();
        add(content);
    }

    public abstract JPanel buildContent();

    public void updatePanel() {
        remove(content);
        content = buildContent();
        add(content);
        revalidate();
        repaint();
    }
}
