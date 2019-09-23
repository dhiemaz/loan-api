package com.java.app.loan.domains.user.services.impl;

import com.java.app.loan.domains.user.models.UserResponse;
import com.java.app.loan.domains.user.repositories.UserRepository;
import com.java.app.loan.domains.user.services.IUserService;
import com.java.app.loan.entities.User;
import com.java.app.loan.enums.ErrorCode;
import com.java.app.loan.models.ErrorBaseRS;
import com.java.app.loan.utils.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ErrorUtil errorUtil;

    @Override
    public UserResponse create(User user) {
        log.info("registering new user {}", user);
        UserResponse userResponse = new UserResponse();
        try {

            //return asyncCallable(() -> userRepository.save(user));
            userRepository.save(user);

            userResponse.setCode("00");
            userResponse.setMessage("request successfully processed.");
        } catch (Exception e) {
            log.error("failed registering new user {}", e);
            ErrorBaseRS error = errorUtil.errorRs(ErrorCode.SOMETHING_UNEXPECTED_HAPPEN);
            userResponse.setError(error);
        }

        return userResponse;
    }
}
