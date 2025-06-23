package controllers;

import core.Controller;
import entities.BusinessUser;
import entities.PersonalUser;
import models.BusinessUserModel;
import models.PersonalUserModel;
import session.SessionManager;
import views.auth.LoginView;

import javax.swing.*;
import java.util.Optional;

public class LoginController extends Controller {
    private LoginView loginView;
    private final PersonalUserModel personalUserModel;
    private final BusinessUserModel businessUserModel;

    public LoginController(PersonalUserModel personalUserModel, BusinessUserModel businessUserModel) {
        this.personalUserModel = personalUserModel;
        this.businessUserModel = businessUserModel;
    }

    @Override
    public void run() {
        loginView = new LoginView(this);
    }

    public void handleRegisterButton() {
        loadLoginView("RegisterView");
    }

    public void handleLoginButton(String dni, String pass) {
        Optional<PersonalUser> pu = personalUserModel.findByDni(dni);
        if (pu.isPresent()) {
            if (pu.get().getPassword().equals(pass)) {
                SessionManager.getInstance().setAttribute("currentUser", pu.get());
                SessionManager.getInstance().setAttribute("sessionLocation", "San Juan de Lurigancho");
                exitLoginView();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.",
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE
                );
            }
            return;
        }

        Optional<BusinessUser> bu = businessUserModel.findByDni(dni);
        if (bu.isPresent()) {
            if (bu.get().getPassword().equals(pass)) {
                SessionManager.getInstance().setAttribute("currentUser", bu.get());
                exitLoginView();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.",
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE
                );
            }
            return;
        }

        JOptionPane.showMessageDialog(null, "No existe una cuenta con ese DNI.",
                "Error de autenticación", JOptionPane.ERROR_MESSAGE
        );
    }

    public LoginView getView() {
        return loginView;
    }
}
