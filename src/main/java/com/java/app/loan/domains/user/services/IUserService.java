package com.java.app.loan.domains.user.services;

import com.java.app.loan.domains.user.models.UserResponse;
import com.java.app.loan.entities.User;

public interface IUserService {
    UserResponse create(User user);
}
