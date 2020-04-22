package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public abstract class BaseTopBar extends JPanel {

    private static final long serialVersionUID = 8688699308325627639L;

    public BaseTopBar() {
        setLayout(new BorderLayout());

        add(buildRightSide(), BorderLayout.EAST);
        add(buildLeftSide(), BorderLayout.WEST);

    }

    public abstract JPanel buildLeftSide();

    private JPanel buildRightSide() {
        JPanel rightSide = new JPanel(new FlowLayout());

        // Search bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 20));
        rightSide.add(searchBar);

        // Profile button
        ImageIcon buttonIcon = new ImageIcon("src/main/resources/user.png");
        JButton profileButton = new JButton(buttonIcon);
        profileButton.setPreferredSize(new Dimension(25, 25));
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildProfileMenu().show((Component) e.getSource(), 0, 0);
            }
        });
        rightSide.add(profileButton);

        return rightSide;
    }

    private JPopupMenu buildProfileMenu() {
        JPopupMenu popup = new JPopupMenu();

        // Manage profile menu item
        JMenuItem profile = new JMenuItem("Manage profile");
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Manage profile clicked");
            }
        });
        popup.add(profile);

        // Log out menu item
        JMenuItem logOut = new JMenuItem("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Log out clicked");
            }
        });
        popup.add(logOut);

        return popup;
    }

}
