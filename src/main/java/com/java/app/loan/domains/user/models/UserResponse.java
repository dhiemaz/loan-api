package com.java.app.loan.domains.user.models;

import com.java.app.loan.models.BaseRS;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse extends BaseRS implements Serializable {
    private String message;
    private String code;
}
