package controllers;

import core.Controller;
import views.app.NotificationView;

public class NotificationController extends Controller {
    private NotificationView notificationView;

    @Override
    public void run() {
        notificationView = new NotificationView();
    }

    public NotificationView getView() {
        return notificationView;
    }
}
