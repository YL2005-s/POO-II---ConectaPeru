package views.auth;

import controllers.LoginController;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JPanel {
    private final LoginController loginController;

    private JTextField tf_dni;
    private JPasswordField pf_password;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;

        make_frame();
        make_lbl_logo();
        make_field_dni();
        make_field_password();
        make_btn_enter();
        make_lbl_register();
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_logo() {
        JLabel lbl_logo = new JLabel();
        lbl_logo.setIcon(ImageUtils.loadIcon("img/logo_cpr.png", 410, 210));
        lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_welcome = new JLabel("Bienvenido", SwingConstants.CENTER);
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        lbl_welcome.setForeground(Color.DARK_GRAY);
        lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lbl_logo, BorderLayout.CENTER);
        add(lbl_welcome, BorderLayout.CENTER);
    }

    private void make_field_dni() {
        JPanel textPanel = new JPanel(new BorderLayout(10, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("DNI");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        tf_dni = new JTextField();
        tf_dni.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_dni.setMargin(new Insets(8, 10, 8, 10));
        tf_dni.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf_dni.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_dni, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 40)));
        add(textPanel);
    }

    private void make_field_password() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(400, 85));
        passwordPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Contraseña");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        lbl_text.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordPanel.add(lbl_text);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 40));

        pf_password = new JPasswordField();
        pf_password.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pf_password.setBounds(0, 0, 400, 40);
        pf_password.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(6, 10, 6, 40)
        ));
        pf_password.setBackground(new Color(245, 245, 245));
        layeredPane.add(pf_password, JLayeredPane.DEFAULT_LAYER);

        JButton btn_eye = new JButton(ImageUtils.loadIcon("icon/icon_eye.png", 22, 22));
        btn_eye.setBounds(360, 9, 22, 22);
        btn_eye.setBorderPainted(false);
        btn_eye.setContentAreaFilled(false);
        btn_eye.setFocusPainted(false);
        btn_eye.setCursor(new Cursor(Cursor.HAND_CURSOR));
        layeredPane.add(btn_eye, JLayeredPane.PALETTE_LAYER);

        passwordPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        passwordPanel.add(layeredPane);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(passwordPanel);

        btn_eye.addActionListener(e -> {
            if (pf_password.getEchoChar() != 0) {
                pf_password.setEchoChar((char) 0);
                btn_eye.setIcon(ImageUtils.loadIcon("icon/icon_closeEye.png", 20, 20));
            } else {
                pf_password.setEchoChar('•');
                btn_eye.setIcon(ImageUtils.loadIcon("icon/icon_eye.png", 20, 20));
            }
        });
    }

    private void make_btn_enter() {
        JButton btn_login = new JButton("Ingresar");
        btn_login.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn_login.setForeground(Color.WHITE);
        btn_login.setBackground(new Color(244, 33, 46));
        btn_login.setFocusPainted(false);
        btn_login.setContentAreaFilled(false);
        btn_login.setOpaque(true);
        btn_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_login.setMaximumSize(new Dimension(300, 45));
        btn_login.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(btn_login);

        btn_login.getModel().addChangeListener(ev -> {
            ButtonModel model = btn_login.getModel();
            if (model.isPressed()) {
                btn_login.setBackground(new Color(200, 0, 0));
            } else {
                btn_login.setBackground(new Color(244, 33, 46));
            }
        });

        btn_login.addActionListener(e -> {
            String dni = tf_dni.getText().trim();
            String password = pf_password.getText().trim();

            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa tu DNI.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa tu contraseña.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!dni.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(null, "El DNI debe contener exactamente 8 dígitos numéricos.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loginController.handleLoginButton(dni, password);
        });
    }

    private void make_lbl_register() {
        JLabel lbl_register = new JLabel("¿No tienes una cuenta?", SwingConstants.CENTER);
        lbl_register.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl_register.setForeground(Color.DARK_GRAY);
        lbl_register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_register.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(lbl_register);

        lbl_register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginController.handleRegisterButton();
            }
        });
    }


}
