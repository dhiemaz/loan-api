package com.java.app.loan.utils;

import com.java.app.loan.enums.ErrorCode;
import com.java.app.loan.models.ErrorBaseRS;
import org.springframework.stereotype.Component;

@Component
public class ErrorUtil {
    public ErrorBaseRS errorRs(ErrorCode error){
        ErrorBaseRS err = new ErrorBaseRS();
        err.setMessage(error.getValue()).setCode(error.getCode());
        return err;
    }

    public ErrorBaseRS errorRs(String errCode) {
        ErrorBaseRS err = new ErrorBaseRS();
        ErrorCode errorCode = mappingSpringErrorCode(errCode);
        err.setMessage(errorCode.getValue()).setCode(errorCode.getCode());
        return err;
    }

    public ErrorBaseRS errorRs(String message, String code) {
        ErrorBaseRS err = new ErrorBaseRS();
        err.setMessage(message).setCode(code);
        return err;
    }

    private ErrorCode mappingSpringErrorCode(String errorCode) {
        if(errorCode == null) {
            return ErrorCode.TRANSACTION_FAILED_FOR_UNKNOWN_REASONS;
        }

        ErrorCode result = ErrorCode.getTransactionTypeByExternalCode(errorCode.toUpperCase());
        return result != null ? result : ErrorCode.TRANSACTION_FAILED_FOR_UNKNOWN_REASONS;
    }
}
