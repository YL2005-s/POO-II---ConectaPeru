package views.auth;

import controllers.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {
    private final RegisterController registerController;

    public RegisterView(RegisterController registerController) {
        this.registerController = registerController;

        make_frame();
    }

    private void make_frame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
    }


}
