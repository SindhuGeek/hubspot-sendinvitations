package com.hubspot.sendinvitaions.api.utils;

public class ApiException extends Exception {

    private int code;
    private String message;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
