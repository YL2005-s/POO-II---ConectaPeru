package session;

import entities.User;

public interface SessionListener {
    void onUserChanged(User user);
}
