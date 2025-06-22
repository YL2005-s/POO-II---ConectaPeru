package views.components;

import controllers.component.SidebarController;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class SidebarComponent extends JPanel {
    private record MenuEntry(String iconPath, String viewName) { }

    private final SidebarController sidebarController;
    private final Map<String, MenuEntry> entries = new LinkedHashMap<>();

    private JPanel headerPanel;
    private JPanel menuPanel;
    private JPanel logOutPanel;
    private boolean isExpanded = false;

    public SidebarComponent(SidebarController sidebarController) {
        this.sidebarController = sidebarController;
        setupEntries();

        make_frame();
        make_headerPanel();
        make_menuPanel();
        make_logOutPanel();
    }

    private void setupEntries() {
        entries.put("Inicio",   new MenuEntry("icon/icon_home.png", "MenuView"));
        entries.put("Perfil",  new MenuEntry("icon/icon_user.png", "ProfileView"));
        entries.put("Notificaciones", new MenuEntry("icon/icon_notification.png", "NotificationView"));
        entries.put("Empleos",  new MenuEntry("icon/icon_suitcase.png", "JobsView"));
        entries.put("Ajustes",  new MenuEntry("icon/icon_settings.png", "SettingsView"));
    }

    private void make_frame() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, Integer.MAX_VALUE));
    }

    private void make_headerPanel() {
        JLabel lbl_toggle = new JLabel(ImageUtils.loadIcon("icon/icon_menu.png", 30, 30));
        lbl_toggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_toggle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.RED);
        headerPanel.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
        headerPanel.add(lbl_toggle);
        add(headerPanel, BorderLayout.NORTH);

        lbl_toggle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMenu();
            }
        });
    }

    private void make_menuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(245, 12, 12));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        entries.forEach(this::addMenuItem);

        menuPanel.setVisible(false);
        add(menuPanel, BorderLayout.CENTER);
    }

    private void addMenuItem(String text, MenuEntry entry) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setBackground(new Color(245, 12, 12));
        itemPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel icon = new JLabel(ImageUtils.loadIcon(entry.iconPath(), 24, 24));
        itemPanel.add(icon);

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        itemPanel.add(label);

        menuPanel.add(itemPanel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entry.viewName() != null) {
                    sidebarController.handleMenuItem(entry.viewName());
                }
            }
        });
    }

    private void make_logOutPanel() {
        logOutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logOutPanel.setBackground(new Color(222, 0, 10));
        logOutPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOutPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel icon = new JLabel(ImageUtils.loadIcon("icon/icon_logout.png", 24, 24));
        icon.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
        logOutPanel.add(icon);

        JLabel label = new JLabel("Cerrar sesión");
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));
        logOutPanel.add(label);

        logOutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        logOutPanel.setVisible(false);
        add(logOutPanel, BorderLayout.SOUTH);
    }

    private void toggleMenu() {
        isExpanded = !isExpanded;
        if (isExpanded) {
            menuPanel.setVisible(true);
            logOutPanel.setVisible(true);
            headerPanel.setPreferredSize(new Dimension(210, 50));
            setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
        } else {
            menuPanel.setVisible(false);
            logOutPanel.setVisible(false);
            headerPanel.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
            setPreferredSize(new Dimension(80, Integer.MAX_VALUE));
        }
        revalidate();
        repaint();
    }
}
