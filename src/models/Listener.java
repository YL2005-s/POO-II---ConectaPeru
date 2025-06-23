package models;

import entities.User;

public interface Listener<T> {
    void onItemChanged(T item);
}
