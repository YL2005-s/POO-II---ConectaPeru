package views.app;

import controllers.ProfileController;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProfileView extends JPanel {
    private final ProfileController profileController;

    private JTextField tf_name;
    private JTextField tf_phone;
    private JTextField tf_email;
    private JTextField tf_location;
    private JLabel lbl_cvFile;

    public ProfileView(ProfileController profileController) {
        this.profileController = profileController;

        make_frame();
        make_lbl_perfil();
        make_profilePanel();
        make_fields();
        make_cvPanel();
        make_btn_save();
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_perfil() {
        JLabel lbl_title = new JLabel("Mi Perfil");
        lbl_title.setFont(new Font("SansSerif", Font.BOLD, 24));
        lbl_title.setForeground(Color.DARK_GRAY);
        lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl_title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lbl_title);
        add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private void make_profilePanel() {
        JPanel picPanel = new JPanel();
        picPanel.setBackground(Color.WHITE);
        picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
        picPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_profilePic = new JLabel();
        lbl_profilePic.setIcon(ImageUtils.loadIcon("icon/icon_user.png", 150, 150));
        lbl_profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        picPanel.add(lbl_profilePic);
        picPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        JLabel lbl_changePic = new JLabel("Cambiar foto");
        lbl_changePic.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lbl_changePic.setForeground(Color.BLUE.darker());
        lbl_changePic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_changePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        picPanel.add(lbl_changePic);

        add(picPanel);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void make_fields() {
        tf_name = make_field("Nombre completo:");
        tf_phone = make_field("Teléfono:");
        tf_email = make_field("Email:");
        tf_location = make_field("Ubicación:");
    }

    private JTextField make_field(String labelText) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(550, 55));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(Color.DARK_GRAY);
        panel.add(lbl, BorderLayout.NORTH);

        JTextField tf = new JTextField();
        tf.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf.setMargin(new Insets(8, 8, 8, 8));
        tf.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf.setBackground(new Color(231, 226, 226));
        panel.add(tf, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(panel);
        return tf;
    }

    private void make_cvPanel() {
        JPanel cvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        cvPanel.setBackground(Color.WHITE);
        cvPanel.setMaximumSize(new Dimension(550, 60));
        cvPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_cv = new JLabel("Currículum Vitae:");
        lbl_cv.setFont(new Font("SansSerif", Font.PLAIN, 16));
        cvPanel.add(lbl_cv, BorderLayout.NORTH);

        lbl_cvFile = new JLabel("(Ningún archivo seleccionado)");
        lbl_cvFile.setFont(new Font("SansSerif", Font.ITALIC, 14));
        lbl_cvFile.setForeground(Color.GRAY);
        cvPanel.add(lbl_cvFile);

        JButton btn_browse = new JButton("Seleccionar");
        btn_browse.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cvPanel.add(btn_browse);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(cvPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void make_btn_save() {
        JButton btn_save = new JButton("Guardar cambios");
        btn_save.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn_save.setForeground(Color.WHITE);
        btn_save.setBackground(new Color(244, 33, 46));
        btn_save.setFocusPainted(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setOpaque(true);
        btn_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_save.setMaximumSize(new Dimension(280, 40));
        btn_save.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(btn_save);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

}
