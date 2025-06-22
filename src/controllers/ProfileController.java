package controllers;

import core.Controller;
import views.app.ProfileView;

public class ProfileController extends Controller {
    private ProfileView profileView;

    @Override
    public void run() {
        profileView = new ProfileView(this);
    }

    public ProfileView getView() {
        return profileView;
    }
}
