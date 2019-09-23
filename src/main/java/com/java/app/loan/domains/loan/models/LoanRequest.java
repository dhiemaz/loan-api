package com.java.app.loan.domains.loan.models;

import com.java.app.loan.models.BaseRS;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NonNull
public class LoanRequest extends BaseRS implements Serializable {
    private int userId;
    private BigDecimal amount;
    private int term;
}
