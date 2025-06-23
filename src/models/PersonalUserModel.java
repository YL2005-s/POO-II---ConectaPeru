package models;

import entities.PersonalUser;
import models.repository.impl.PersonalUserRepository;

import java.util.ArrayList;
import java.util.List;

public class PersonalUserModel  extends UserModel<PersonalUser> {

    public PersonalUserModel() {
        super(new PersonalUserRepository());
    }
}