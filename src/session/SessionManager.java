package session;

import entities.User;
import entities.Vacant;
import models.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    private final Map<String, Object> data = new HashMap<>();
    private final List<Listener<User>> userListeners = new ArrayList<>();

    private SessionManager() {}

    public void addUserListener(Listener<User> listener) {
        userListeners.add(listener);
    }

    public void removeUserListener(Listener<User> listener) {
        userListeners.remove(listener);
    }

    public void setAttribute(String key, Object value) {
        data.put(key, value);

        if ("currentUser".equals(key)) {
            User u = (User) value;
            for (Listener<User> l : userListeners) {
                l.onItemChanged(u);
            }
        }
    }

    public <T> T getAttribute(String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    public Object getAttribute(String key) {
        return data.get(key);
    }

    public void clear() {
        data.clear();

        for (Listener<User> l : userListeners) {
            l.onItemChanged(null);
        }
    }

    public static SessionManager getInstance() {
        return instance;
    }
}
