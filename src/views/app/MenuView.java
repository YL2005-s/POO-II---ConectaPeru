package views.app;

import controllers.MenuController;
import entities.User;
import session.SessionListener;
import session.SessionManager;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuView extends JPanel implements SessionListener {
    private final MenuController menuController;

    private JPanel jobsPanel;
    private JLabel lbl_welcome;
    private JLabel lbl_location;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;

        make_frame();
        make_lbl_logo();
        make_locationPanel();
        make_searchPanel();
        make_jobsPanel();

        SessionManager.getInstance().addListener(this);
    }

    @Override
    public void onUserChanged(User user) {
        SwingUtilities.invokeLater(() -> {
            if (user != null) {
                lbl_welcome.setText("Hola, " + user.getNombre().split("\\s+")[0]);
                lbl_location.setText((String) SessionManager.getInstance().getAttribute("sessionLocation"));
            } else {
                lbl_welcome.setText("Hola, Invitado");
                lbl_location.setText("San Juan de Lurigancho");
            }
        });
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_logo() {
        JLabel lbl_logo = new JLabel();
        lbl_logo.setIcon(ImageUtils.loadIcon("img/logo_cpr.png", 350, 140));
        lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lbl_welcome = new JLabel("Hola, Invitado", SwingConstants.CENTER);
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbl_welcome.setForeground(Color.DARK_GRAY);
        lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lbl_logo, BorderLayout.CENTER);
        add(lbl_welcome, BorderLayout.CENTER);
    }

    private void make_locationPanel() {
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        locationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        locationPanel.setBackground(Color.WHITE);

        JLabel icon = new JLabel(ImageUtils.loadIcon("icon/icon_location.png", 20, 20));
        locationPanel.add(icon);

        lbl_location = new JLabel("San Juan de Lurigancho");
        lbl_location.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_location.setForeground(Color.GRAY);
        locationPanel.add(lbl_location);

        locationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(locationPanel);
    }

    private void make_searchPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setMaximumSize(new Dimension(600, 50));
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        JTextField tf_search = new JTextField();
        tf_search.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_search.setMargin(new Insets(8, 10, 8, 10));
        tf_search.setBackground(new Color(245, 245, 245));
        tf_search.setToolTipText("Busca ofertas de empleo...");
        wrapper.add(tf_search, BorderLayout.CENTER);

        add(wrapper);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void make_jobsPanel() {
        JLabel lbl_section = new JLabel("Trabajos recientes");
        lbl_section.setFont(new Font("SansSerif", Font.BOLD, 26));
        lbl_section.setForeground(Color.DARK_GRAY);
        lbl_section.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl_section);
        add(Box.createRigidArea(new Dimension(0, 15)));

        jobsPanel = new JPanel();
        jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
        jobsPanel.setBackground(Color.WHITE);
        jobsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addJobItem("Desarrollador Java", "Empresa A");
        addJobItem("Analista de Datos", "Empresa B");
        addJobItem("Analista de USIL", "Empresa C");

        add(jobsPanel);
    }

    private void addJobItem(String title, String company) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setMaximumSize(new Dimension(650, 60));
        wrapper.setBorder(new EmptyBorder(0, 20, 0, 20));
        wrapper.setBackground(Color.WHITE);

        JPanel jobPanel = new JPanel(new BorderLayout());
        jobPanel.setBackground(new Color(245, 245, 245));
        jobPanel.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel lbl_title = new JLabel(title);
        lbl_title.setFont(new Font("SansSerif", Font.BOLD, 16));
        jobPanel.add(lbl_title, BorderLayout.NORTH);

        JLabel lbl_company = new JLabel(company);
        lbl_company.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl_company.setForeground(Color.GRAY);
        jobPanel.add(lbl_company, BorderLayout.SOUTH);
        jobPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        wrapper.add(jobPanel, BorderLayout.CENTER);
        jobsPanel.add(wrapper);
        jobsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

}
