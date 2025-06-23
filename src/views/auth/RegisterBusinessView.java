package views.auth;

import controllers.RegisterBusinessController;
import entities.BusinessUser;
import entities.PersonalUser;
import entities.Role;
import entities.User;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterBusinessView extends JPanel {
    private final RegisterBusinessController registerBusinessController;

    private JTextField tf_fullName;
    private JTextField tf_dni;
    private JTextField tf_companyName;
    private JTextField tf_companySector;
    private JPasswordField pf_password;

    public RegisterBusinessView(RegisterBusinessController registerBusinessController) {
        this.registerBusinessController = registerBusinessController;

        make_frame();
        make_lbl_logo();
        make_field_fullName();
        make_field_dni();
        make_field_companyName();
        make_field_companySector();
        make_field_password();
        make_btn_register();
        make_lbl_goBack();
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }

    private void make_lbl_logo() {
        JLabel lbl_logo = new JLabel();
        lbl_logo.setIcon(ImageUtils.loadIcon("img/logo_cpr.png", 300, 160));
        lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_welcome = new JLabel("Registro de Usuario Empresa", SwingConstants.CENTER);
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        lbl_welcome.setForeground(Color.DARK_GRAY);
        lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lbl_logo, BorderLayout.CENTER);
        add(lbl_welcome, BorderLayout.CENTER);
    }

    private void make_field_fullName() {
        JPanel textPanel = new JPanel(new BorderLayout(10, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Nombre completo");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        tf_fullName = new JTextField();
        tf_fullName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_fullName.setMargin(new Insets(8, 10, 8, 10));
        tf_fullName.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf_fullName.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_fullName, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(textPanel);
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

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(textPanel);
    }

    private void make_field_companyName() {
        JPanel textPanel = new JPanel(new BorderLayout(10, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Razón Social");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        tf_companyName = new JTextField();
        tf_companyName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_companyName.setMargin(new Insets(8, 10, 8, 10));
        tf_companyName.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf_companyName.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_companyName, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(textPanel);
    }

    private void make_field_companySector() {
        JPanel textPanel = new JPanel(new BorderLayout(10, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Sector");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        tf_companySector = new JTextField();
        tf_companySector.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_companySector.setMargin(new Insets(8, 10, 8, 10));
        tf_companySector.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        tf_companySector.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_companySector, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(textPanel);
    }

    private void make_field_password() {
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 5));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(400, 66));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl = new JLabel("Contraseña");
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(Color.DARK_GRAY);
        passwordPanel.add(lbl, BorderLayout.NORTH);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 22));

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

        passwordPanel.add(layeredPane, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 10)));
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

    private void make_btn_register() {
        JButton btn = new JButton("Registrar");
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(244, 33, 46));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 45));

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(btn);

        btn.addActionListener(e -> {
            String fullName = tf_fullName.getText().trim();
            String dni = tf_dni.getText().trim();
            String razon = tf_companyName.getText().trim();
            String sector = tf_companySector.getText().trim();
            String password = new String(pf_password.getPassword()).trim();

            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa tu nombre completo.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa tu DNI.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!dni.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "El DNI debe contener exactamente 8 dígitos numéricos.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa tu contraseña.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (razon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa tu razón social.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sector.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa tu sector.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new BusinessUser.Builder()
                    .nombre(fullName)
                    .dni(dni)
                    .password(password)
                    .rol(Role.ADMINISTRADOR)
                    .razonSocial(razon)
                    .sector(sector)
                    .build();

            boolean suceed = registerBusinessController.handleRegisterButton(user);
            if (suceed) {
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                registerBusinessController.handleLoginButton();
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un error al crear el usuario",
                        "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void make_lbl_goBack() {
        JLabel lbl_register = new JLabel("Volver al Inicio de Sesión", SwingConstants.CENTER);
        lbl_register.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl_register.setForeground(Color.DARK_GRAY);
        lbl_register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_register.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 5)));
        add(lbl_register);

        lbl_register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerBusinessController.handleLoginButton();
            }
        });
    }

}
