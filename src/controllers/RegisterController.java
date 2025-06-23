package controllers;

import core.Controller;
import entities.PersonalUser;
import entities.User;
import models.PersonalUserModel;
import views.auth.RegisterView;

public class RegisterController extends Controller {
    private RegisterView registerView;
    private final PersonalUserModel personalUserModel;

    public RegisterController(PersonalUserModel personalUserModel) {
        this.personalUserModel = personalUserModel;
    }

    @Override
    public void run() {
        this.registerView = new RegisterView(this);
    }

    public boolean handleRegisterButton(User user) {
        try {
            personalUserModel.saveUser((PersonalUser) user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleRegisterBusinessButton() {
        loadLoginView("RegisterBusinessView");
    }

    public void handleLoginButton() {
        loadLoginView("LoginView");
    }

    public RegisterView getView() {
        return registerView;
    }
}
