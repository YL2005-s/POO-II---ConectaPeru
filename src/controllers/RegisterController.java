package controllers;

import core.Controller;
import views.auth.RegisterView;

public class RegisterController extends Controller {
    private RegisterView registerView;

    @Override
    public void run() {
        this.registerView = new RegisterView(this);
    }

    public void handleLoginButton() {
        loadLoginView("LoginView");
    }

    public RegisterView getView() {
        return registerView;
    }
}
