package com.java.app.loan.domains.loan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.java.app.loan.models.BaseRS;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse extends BaseRS {
    private String message;
    private String code;
}
