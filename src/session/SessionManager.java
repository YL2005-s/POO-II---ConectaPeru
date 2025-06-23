package session;

import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    private final Map<String, Object> data = new HashMap<>();
    private final List<SessionListener> listeners = new ArrayList<>();

    private SessionManager() {}

    public void addListener(SessionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SessionListener listener) {
        listeners.remove(listener);
    }

    public void setAttribute(String key, Object value) {
        data.put(key, value);

        if ("currentUser".equals(key)) {
            User u = (User) value;
            for (SessionListener l : listeners) {
                l.onUserChanged(u);
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

        for (SessionListener l : listeners) {
            l.onUserChanged(null);
        }
    }

    public static SessionManager getInstance() {
        return instance;
    }
}
