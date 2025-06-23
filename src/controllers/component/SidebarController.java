package controllers.component;

import core.Controller;
import session.SessionManager;
import views.components.SidebarComponent;

public class SidebarController extends Controller {
    public SidebarComponent sidebarComponent;

    @Override
    public void run() {
        sidebarComponent = new SidebarComponent(this);
    }

    public void handleMenuItem(String view) {
        loadView(view);
    }

    public void handleLogoutItem() {
        SessionManager.getInstance().clear();
        exitMenuView();
    }

    public SidebarComponent getSidebarComponent() {
        return sidebarComponent;
    }
}
