package com.java.app.loan.exceptions;

import com.java.app.loan.enums.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class InvalidValidationException extends RuntimeException {
    private static final long serialVersionUID = -3501278421951263626L;
    private static String ENUM_ERROR_FORMAT = "value : %s , code : %s";

    private ErrorCode enumErrorCode;
    private String errCode;
    private String errDetail;

    public InvalidValidationException(String message) {
        super(message);
    }

    public InvalidValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValidationException(ErrorCode enumErrorCode, Throwable cause) {
        super(enumErrorCode.getValue(), cause);
        this.enumErrorCode = enumErrorCode;
    }

    public InvalidValidationException(ErrorCode enumErrorCode) {
        super(composeErrMessage(enumErrorCode));
        this.enumErrorCode = enumErrorCode;
    }

    public InvalidValidationException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public InvalidValidationException(
            @Nullable ErrorCode enumErrorCode,
            @Nullable Set<ConstraintViolation<?>> violations) {
        super(composeErrMessage(enumErrorCode));
        this.enumErrorCode = enumErrorCode;
        this.errDetail = composeErrDetail(violations);
    }

    public ErrorCode getEnumErrorCode() {
        return this.enumErrorCode;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrDetail() {
        return errDetail;
    }

    private static String composeErrMessage(@Nullable ErrorCode enumErrorCode) {
        if (null == enumErrorCode) {
            return StringUtils.EMPTY;
        }
        return String.format(ENUM_ERROR_FORMAT, enumErrorCode.getValue(), enumErrorCode.getCode());
    }

    private static String composeErrDetail(@Nullable Set<ConstraintViolation<?>> violations) {
        if (null == violations) {
            return StringUtils.EMPTY;
        }

        final StringBuilder builder = new StringBuilder();
        violations.forEach(violation -> {
            builder.append(violation.getPropertyPath()).append(" => ").append(violation.getMessage());
        });
        return builder.toString();
    }
}
