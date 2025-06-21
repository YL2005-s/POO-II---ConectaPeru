package controllers;

import core.Controller;
import views.auth.LoginView;
import views.app.MenuView;
import views.auth.RegisterView;

public class MenuController extends Controller {
    private final MenuView menuView = new MenuView(this);

    private final LoginController loginController = new LoginController();
    private final RegisterController registerController = new RegisterController();

    @Override
    public void run() {
        loginController.run();
        registerController.run();

        addLoginView("LoginView", getLoginView());
        addLoginView("RegisterView", getRegisterView());

        mainFrame.setVisible(true);
    }

    public LoginView getLoginView() {
        return loginController.getView();
    }

    public RegisterView getRegisterView() {
        return registerController.getView();
    }


}
