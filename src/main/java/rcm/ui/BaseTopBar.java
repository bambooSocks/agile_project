package rcm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import rcm.model.Application;
import rcm.ui.popup.ProfileView;

public abstract class BaseTopBar extends JPanel {

    private static final long serialVersionUID = 8688699308325627639L;

    protected Application app;
    private boolean showSearchBar = true;
    private JButton searchButton;
    private String searchCommand;

    public BaseTopBar(Application app, String searchCommand) {
        this(app, searchCommand, true);
    }

    public BaseTopBar(Application app, String searchCommand, boolean showSearchBar) {
        setLayout(new BorderLayout());

        this.app = app;
        this.showSearchBar = showSearchBar;
        this.searchCommand = searchCommand;

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
            search.setPreferredSize(new Dimension(200, 22));
            search.setBorder(new LineBorder(Color.gray));

            ImageIcon image = new ImageIcon("src/main/resources/search_icon.jpg");
            searchButton = new JButton(image);
            searchButton.setPreferredSize(new Dimension(16, 16));
            searchButton.setBorder(null);
            searchButton.addKeyListener(new KeyListener());

            JTextField searchBar = new JTextField();
            searchBar.setBorder(new LineBorder(Color.white));
            searchBar.setPreferredSize(new Dimension(150, 20));
            searchBar.addKeyListener(new KeyListener());

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.fireChange(searchCommand, searchBar.getText());
                }
            });

            ImageIcon X = new ImageIcon("src/main/resources/clear.jpg");
            JButton clearButton = new JButton(X);
            clearButton.setPreferredSize(new Dimension(16, 16));
            clearButton.setBorder(null);
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchBar.setText("");
                    app.fireChange(searchCommand, "");
                }
            });
//            search.add(magni);
            search.add(searchButton);
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

                ProfileView popup = new ProfileView(app);
                // TODO: For testing purposes (Adrienne and Viktor):
//                EnterStatusView popup = new EnterStatusView();      // in place (need to double check)
//                JourneyShareView popup = new JourneyShareView();      // in place
//                AdvancedSearchView popup = new AdvancedSearchView();

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

    class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                searchButton.doClick();
            }
        }
    }
}
