package views.app;

import controllers.MenuController;
import entities.User;
import entities.Vacant;
import models.Listener;
import session.SessionManager;
import utils.ImageUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuView extends JPanel implements Listener<User>   {
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

        SessionManager.getInstance().addUserListener(this);
    }

    @Override
    public void onItemChanged(User user) {
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

        tf_search.addActionListener(e -> {
            String query = tf_search.getText().trim();
            menuController.searchVacantes(query);
        });
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

        add(jobsPanel);
    }

    public void setupVacant(List<Vacant> vacant) {
        jobsPanel.removeAll();

        for (Vacant va : vacant) {
            jobsPanel.add(addJobItem(va));
            jobsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        jobsPanel.revalidate();
        jobsPanel.repaint();
    }

    private JPanel addJobItem(Vacant vacant) {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setMaximumSize(new Dimension(650, 60));
        wrap.setBackground(Color.WHITE);

        JPanel jp = new JPanel(new BorderLayout());
        jp.setBackground(new Color(245, 245, 245));
        jp.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel title = new JLabel(vacant.getTitulo());
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        jp.add(title, BorderLayout.NORTH);

        JLabel info = new JLabel("S/ " + vacant.getSalario() + " • " + vacant.getUbicacion());
        info.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jp.add(info, BorderLayout.SOUTH);

        jp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jp.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                User user = SessionManager.getInstance().getAttribute("currentUser", User.class);
                String dni = (user != null) ? user.getDni() : "";

                menuController.postulate(vacant, dni);
            }
        });

        wrap.add(jp, BorderLayout.CENTER);
        return wrap;
    }

    public void showPostulationSuccess(Vacant v) {
        JOptionPane.showMessageDialog(this, "Te has postulado exitosamente a " + v.getTitulo(),
                "Postulación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showPostulationError(Vacant v) {
        JOptionPane.showMessageDialog(this, "Error al postular a " + v.getTitulo(),
                "Postulación fallida", JOptionPane.ERROR_MESSAGE);
    }
}
