package com.java.app.loan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRS {
    private ErrorBaseRS error;

    public ErrorBaseRS getError() {
        return error;
    }

    public BaseRS setError(ErrorBaseRS error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseRS{");
        sb.append("error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
