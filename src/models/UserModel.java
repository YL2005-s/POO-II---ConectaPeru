package models;

import entities.User;
import models.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class UserModel<T extends User> {
    protected final Repository<T> repository;
    protected final List<T> users = new ArrayList<>();

    protected UserModel(Repository<T> repository) {
        this.repository = repository;
    }

    public void saveUser(T user) {
        try {
            repository.create(user);
            users.add(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadUsers() {
        try {
            users.clear();
            users.addAll(repository.list());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Optional<T> findByDni(String dni) {
        return users.stream()
                .filter(u -> u.getDni().equals(dni))
                .findFirst();
    }

    public List<T> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
