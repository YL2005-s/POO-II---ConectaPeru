package controllers;

import controllers.component.SidebarController;
import core.Controller;
import models.BusinessUserModel;
import models.PersonalUserModel;
import views.app.ProfileView;
import views.auth.LoginView;
import views.app.MenuView;
import views.auth.RegisterBusinessView;
import views.auth.RegisterView;

public class MenuController extends Controller {
    private final MenuView menuView = new MenuView(this);
    private final PersonalUserModel personalUserModel = new PersonalUserModel();
    private final BusinessUserModel businessUserModel = new BusinessUserModel();

    private final LoginController loginController = new LoginController(personalUserModel, businessUserModel);
    private final RegisterController registerController = new RegisterController(personalUserModel);
    private final RegisterBusinessController registerBusinessController = new RegisterBusinessController(businessUserModel);
    private final SidebarController sidebarController = new SidebarController();
    private final ProfileController profileController = new ProfileController();

    @Override
    public void run() {
        personalUserModel.loadUsers();
        businessUserModel.loadUsers();

        loginController.run();
        registerController.run();
        registerBusinessController.run();
        sidebarController.run();
        profileController.run();

        addLoginView("LoginView", getLoginView());
        addLoginView("RegisterView", getRegisterView());
        addLoginView("RegisterBusinessView", getRegisterBusinessView());
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

    public RegisterBusinessView getRegisterBusinessView() {
        return registerBusinessController.getView();
    }

    public ProfileView getProfileView() {
        return profileController.getView();
    }


}
