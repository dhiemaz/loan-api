package com.java.app.loan.enums;

public enum ErrorCode {
    REQUEST_NOT_VALID("request is not valid!", 74, "0075"),
    SOMETHING_UNEXPECTED_HAPPEN("something unexpected happen in server",60,"0061"),
    TRANSACTION_FAILED_FOR_UNKNOWN_REASONS("Transaction Failed For Unknown Reasons",40,"0046"),
    TRANSACTION_NOT_FOUND("transaction not Found",48,"0009"),
    USER_NOT_FOUND("user is not found!",45,"0070"),
    DATA_NOT_FOUND("data is not found!",46,""),
    SERVICE_IS_UNAVAILABLE("sorry service is unavailable for now!", 83, "0100"),
    CANNOT_PROCESS_TRANSACTION("Your transaction cannot be processed!", 83, "0101");

    final String value;
    final int typeId;
    final String code;
    final String externalCode;

    ErrorCode(String value, int typeId, String code) {
        this.value = value;
        this.typeId = typeId;
        this.code = code;
        this.externalCode = "";
    }

    public String getValue() {
        return value;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getCode() {
        return code;
    }

    public String getExternalCode(){return externalCode;}

    public static int getOrdinalBasedOnValue(String value) {
        try {
            return ErrorCode.valueOf(value.replace(" ", "_")).getTypeId();
        } catch (Exception e) {
            return 0;
        }
    }

    public static ErrorCode getTransactionTypeByCode(String code) {
        for (ErrorCode type : ErrorCode.values()) {
            if(type.getCode().equalsIgnoreCase(code)){
                return type;
            }
        }
        return null;
    }

    public static ErrorCode getTransactionTypeByExternalCode(String externalCode) {
        for (ErrorCode type : ErrorCode.values()) {
            if(type.getExternalCode().equalsIgnoreCase(externalCode)){
                return type;
            }
        }
        return null;
    }

    public static ErrorCode getTransactionTypeByValue(String value) {
        for (ErrorCode type : ErrorCode.values()) {
            if(value.equals(type.getValue())){
                return type;
            }
        }
        return null;
    }
}
