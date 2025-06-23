package views.app;

import controllers.JobsController;
import entities.User;
import entities.Vacant;
import models.Listener;
import session.SessionManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JobsView extends JPanel implements Listener<User> {
    private final JobsController jobsController;

    private final JPanel pendingPanel = new JPanel();
    private final JPanel acceptedPanel = new JPanel();
    private final JPanel rejectedPanel = new JPanel();
    private final JTextField tf_search = new JTextField();

    public JobsView(JobsController jobsController) {
        this.jobsController = jobsController;
        initComponents();
    }

    private void initComponents() {
        make_frame();
        make_lbl_title();
        make_searchPanel();
        make_section("Pendientes",  pendingPanel);
        make_section("Aceptadas",   acceptedPanel);
        make_section("Rechazadas",  rejectedPanel);

        SessionManager.getInstance().addUserListener(this);
    }

    @Override
    public void onItemChanged(User user) {
        SwingUtilities.invokeLater(() -> {
            jobsController.onUserChanged(user);
        });
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_title() {
        JLabel lbl = new JLabel("Mis postulaciones", SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void make_searchPanel() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        wrap.setBackground(Color.WHITE);
        wrap.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 20, 0, 20),
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true)
        ));

        tf_search.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_search.setMargin(new Insets(8, 10, 8, 10));
        tf_search.setToolTipText("Filtrar por título...");
        wrap.add(tf_search, BorderLayout.CENTER);
        add(wrap);
        add(Box.createRigidArea(new Dimension(0, 15)));

        tf_search.addActionListener(e -> {
            String query = tf_search.getText().trim();
            jobsController.filterPostulations(query);
        });
    }

    private void make_section(String title, JPanel panel) {
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 20));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl);
        add(Box.createRigidArea(new Dimension(0, 10)));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        scroll.setMaximumSize(new Dimension(700, 150));
        add(scroll);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public void setPostulations(List<Vacant> pending, List<Vacant> accepted, List<Vacant> rejected) {
        populateSection(pendingPanel,  pending);
        populateSection(acceptedPanel, accepted);
        populateSection(rejectedPanel, rejected);
    }

    private void populateSection(JPanel panel, List<Vacant> items) {
        panel.removeAll();
        for (Vacant v : items) {
            panel.add(createJobItem(v));
            panel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        panel.revalidate();
        panel.repaint();
    }

    private JPanel createJobItem(Vacant vacant) {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setMaximumSize(new Dimension(650, 60));
        wrap.setBackground(Color.WHITE);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel lbl_title = new JLabel(vacant.getTitulo());
        lbl_title.setFont(new Font("SansSerif", Font.BOLD, 16));
        card.add(lbl_title, BorderLayout.NORTH);

        JLabel lbl_info = new JLabel("S/ " + vacant.getSalario() + " • " + vacant.getUbicacion());
        lbl_info.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(lbl_info, BorderLayout.SOUTH);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jobsController.viewDetails(vacant);
            }
        });

        wrap.add(card, BorderLayout.CENTER);
        return wrap;
    }

}
