package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import rcm.model.Application;

public abstract class BaseTopBar extends JPanel {

    private static final long serialVersionUID = 8688699308325627639L;

    private Application app;
    private boolean showSearchBar = true;
    
    public BaseTopBar(Application app) {
        this(app, true);
    }
    
    public BaseTopBar(Application app, boolean showSearchBar) {
        setLayout(new BorderLayout());

        this.app = app;
        this.showSearchBar = showSearchBar;
        
        add(buildRightSide(), BorderLayout.EAST);
        add(buildLeftSide(), BorderLayout.WEST);
        setPreferredSize(new Dimension(500, 40));
    }

    public abstract JPanel buildLeftSide();

    private JPanel buildRightSide() {
        JPanel rightSide = new JPanel(new FlowLayout());

        // Search bar
        if (showSearchBar) {
            FlowLayout searchLayout = new FlowLayout();
            searchLayout.setVgap(0);
            JPanel search = new JPanel(searchLayout);
            search.setBackground(Color.white);
            search.setPreferredSize(new Dimension(200, 22)); // not really working, still kinda tall imo...
            search.setBorder(new LineBorder(Color.gray));
            
            ImageIcon image = new ImageIcon("src/main/resources/search_icon.jpg");
            JLabel magni = new JLabel(image);
            magni.setPreferredSize(new Dimension(16, 16));
            
            JTextField searchBar = new JTextField();
            searchBar.setBorder(new LineBorder(Color.white));
            searchBar.setPreferredSize(new Dimension(150, 20));
            
            ImageIcon X = new ImageIcon("src/main/resources/clear.jpg");
            JButton clearButton = new JButton(X);
            clearButton.setPreferredSize(new Dimension(16, 16));
            clearButton.setBorder(null);
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchBar.setText("");
                }
            });
            search.add(magni);
            search.add(searchBar);
            search.add(clearButton);
            rightSide.add(search);
        }
        
        // Profile button
        ImageIcon buttonIcon = new ImageIcon("src/main/resources/user.png");
        JButton profileButton = new JButton(buttonIcon);
        profileButton.setPreferredSize(new Dimension(25, 25));
        profileButton.setBorder(null);
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
                ProfileView popup = new ProfileView();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });
        popup.add(profile);

        // Log out menu item
        JMenuItem logOut = new JMenuItem("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.logOut();
            }
        });
        popup.add(logOut);

        return popup;
    }

}
