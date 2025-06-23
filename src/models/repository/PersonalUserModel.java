package models.repository;

import entities.PersonalUser;
import models.Database;
import models.repository.impl.PersonalUserRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PersonalUserModel {
    private final PersonalUserRepository repository;
    private final List<PersonalUser> personalUsers;
    private String notice;

    public PersonalUserModel() {
        this.repository = new PersonalUserRepository();
        this.personalUsers = new ArrayList<>();
    }

    public String saveUser(PersonalUser user) {
        try {
            repository.create(user);
            personalUsers.add(user);
            notice = "Usuario creado exitosamente";
        } catch (Exception ex) {
            notice = "Error al crear usuario";
        }

        return notice;
    }




}
