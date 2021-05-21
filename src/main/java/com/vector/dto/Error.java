package com.vector.dto;

import javax.validation.constraints.NotBlank;

/***
 * An error
 */
public class Error {
    @NotBlank(message = "code is required")
    private String code;

    @NotBlank(message = "message is required")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
