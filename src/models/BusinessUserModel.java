package models;

import entities.BusinessUser;
import models.repository.impl.BusinessUserRepository;

import java.util.ArrayList;
import java.util.List;

public class BusinessUserModel extends UserModel<BusinessUser> {

    public BusinessUserModel() {
        super(new BusinessUserRepository());
    }
}