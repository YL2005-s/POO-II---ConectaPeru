package controllers;

import controllers.component.SidebarController;
import core.Controller;
import views.app.ProfileView;
import views.auth.LoginView;
import views.app.MenuView;
import views.auth.RegisterView;
import views.components.SidebarComponent;

public class MenuController extends Controller {
    private final MenuView menuView = new MenuView(this);

    private final LoginController loginController = new LoginController();
    private final RegisterController registerController = new RegisterController();
    private final SidebarController sidebarController = new SidebarController();
    private final ProfileController profileController = new ProfileController();

    @Override
    public void run() {
        loginController.run();
        registerController.run();
        sidebarController.run();
        profileController.run();

        addLoginView("LoginView", getLoginView());
        addLoginView("RegisterView", getRegisterView());
        addView("MenuView", menuView);
        addView("ProfileView", getProfileView());

        setViewerSidebar(sidebarController.getSidebarComponent());


        mainFrame.setVisible(true);
    }

    public LoginView getLoginView() {
        return loginController.getView();
    }

    public RegisterView getRegisterView() {
        return registerController.getView();
    }

    public ProfileView getProfileView() {
        return profileController.getView();
    }


}
