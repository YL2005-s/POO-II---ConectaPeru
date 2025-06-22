package views.app;

import controllers.MenuController;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuView extends JPanel {
    private final MenuController menuController;

    private JPanel recentJobsPanel;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;

        make_frame();
        make_lbl_logo();
        make_locationPanel();
        make_searchPanel();
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_logo() {
        JLabel lbl_logo = new JLabel();
        lbl_logo.setIcon(ImageUtils.loadIcon("img/logo_cpr.png", 370, 130));
        lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_welcome = new JLabel("Hola, Usuario", SwingConstants.CENTER);
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbl_welcome.setForeground(Color.DARK_GRAY);
        lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lbl_logo, BorderLayout.CENTER);
        add(lbl_welcome, BorderLayout.CENTER);
    }

    private void make_locationPanel() {
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        locationPanel.setBackground(Color.WHITE);

        JLabel icon = new JLabel(ImageUtils.loadIcon("icon/icon_location.png", 20, 20));
        locationPanel.add(icon);

        JLabel lbl_location = new JLabel("San Juan de Lurigancho");
        lbl_location.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_location.setForeground(Color.GRAY);
        locationPanel.add(lbl_location);

        JLabel btnChange = new JLabel(ImageUtils.loadIcon("icon/icon_edit.png", 18, 18));
        btnChange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnChange.setToolTipText("Cambiar ubicación");
        locationPanel.add(btnChange);

        locationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(locationPanel);
    }

    private void make_searchPanel() {
        // Panel wrapper que se expandirá horizontalmente
        JPanel wrapper = new JPanel(new BorderLayout());
        // Permitimos que BoxLayout lo estire en X hasta el máximo
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        // Margen izquierdo/derecho de 20px para no ocupar TODO el ancho
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        wrapper.setBackground(Color.WHITE);

        // El propio text field, con un tamaño preferido razonable
        JTextField tf_search = new JTextField();
        tf_search.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_search.setMargin(new Insets(8, 10, 8, 10));
        tf_search.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf_search.setBackground(new Color(245, 245, 245));

        // Lo añadimos al centro del wrapper, para que se expanda ahí
        wrapper.add(tf_search, BorderLayout.CENTER);

        // Finalmente lo agregamos a la vista
        add(wrapper);
        add(Box.createRigidArea(new Dimension(0, 30)));
    }





}
