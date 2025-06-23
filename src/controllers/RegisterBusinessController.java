package controllers;

import core.Controller;
import entities.BusinessUser;
import entities.PersonalUser;
import entities.User;
import models.BusinessUserModel;
import views.auth.RegisterBusinessView;

public class RegisterBusinessController extends Controller {
    private RegisterBusinessView registerBusinessView;
    private final BusinessUserModel businessUserModel;

    public RegisterBusinessController(BusinessUserModel businessUserModel) {
        this.businessUserModel = businessUserModel;
    }

    @Override
    public void run() {
        registerBusinessView = new RegisterBusinessView(this);
    }

    public boolean handleRegisterButton(User user) {
        try {
            businessUserModel.saveUser((BusinessUser) user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleLoginButton() {
        loadLoginView("LoginView");
    }

    public RegisterBusinessView getView() {
        return registerBusinessView;
    }
}
