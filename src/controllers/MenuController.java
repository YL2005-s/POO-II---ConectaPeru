package controllers;

import controllers.component.SidebarController;
import core.Controller;
import entities.Vacant;
import models.BusinessUserModel;
import models.PersonalUserModel;
import models.VacanteModel;
import session.SessionManager;
import views.app.JobsView;
import views.app.ProfileView;
import views.auth.LoginView;
import views.app.MenuView;
import views.auth.RegisterBusinessView;
import views.auth.RegisterView;

public class MenuController extends Controller {
    private final MenuView menuView = new MenuView(this);
    private final PersonalUserModel personalUserModel = new PersonalUserModel();
    private final BusinessUserModel businessUserModel = new BusinessUserModel();
    private final VacanteModel vacanteModel = new VacanteModel();

    private final LoginController loginController = new LoginController(personalUserModel, businessUserModel);
    private final RegisterController registerController = new RegisterController(personalUserModel);
    private final RegisterBusinessController registerBusinessController = new RegisterBusinessController(businessUserModel);
    private final SidebarController sidebarController = new SidebarController();
    private final ProfileController profileController = new ProfileController();
    private final JobsController jobsController = new JobsController();

    @Override
    public void run() {
        personalUserModel.loadUsers();
        businessUserModel.loadUsers();
        vacanteModel.loadVacantes();

        menuView.setupVacant(vacanteModel.search("San Juan de Lurigancho", ""));

        loginController.run();
        registerController.run();
        registerBusinessController.run();
        sidebarController.run();
        profileController.run();
        jobsController.run();

        addLoginView("LoginView", getLoginView());
        addLoginView("RegisterView", getRegisterView());
        addLoginView("RegisterBusinessView", getRegisterBusinessView());
        addView("MenuView", menuView);
        addView("ProfileView", getProfileView());
        addView("JobsView", getJobsView());

        setViewerSidebar(sidebarController.getSidebarComponent());


        mainFrame.setVisible(true);
    }

    public void postulate(Vacant vacant, String dni) {
        boolean success = vacanteModel.postulate(vacant.getId(), dni);
        menuView.setupVacant(vacanteModel.getVacantes());
        if (success) {
            menuView.showPostulationSuccess(vacant);
        } else {
            menuView.showPostulationError(vacant);
        }
    }

    public void searchVacantes(String query) {
        String loc = SessionManager.getInstance().getAttribute("sessionLocation", String.class);
        menuView.setupVacant(vacanteModel.search(loc, query));
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

    public JobsView getJobsView() {
        return jobsController.getView();
    }


}
