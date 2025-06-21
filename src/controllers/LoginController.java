package controllers;

import core.Controller;
import views.auth.LoginView;

public class LoginController extends Controller {
    private LoginView loginView;

    @Override
    public void run() {
        loginView = new LoginView(this);
    }

    public void handleRegisterButton() {
        loadLoginView("RegisterView");
    }

    public void handleLoginButton(String dni, String pass) {

    }

    public LoginView getView() {
        return loginView;
    }
}
