package com.mercadolivre.socialmeli.model;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    Integer httpStatus;
    String message;

    public ErrorMessage(Integer httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
