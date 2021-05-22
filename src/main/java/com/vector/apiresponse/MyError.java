package com.vector.response;

import javax.validation.constraints.NotBlank;

public class MyError {
    public MyError(){

    }

    public MyError(@NotBlank(message = "code is required") String code, @NotBlank(message = "message is required") String message) {
        this.code = code;
        this.message = message;
    }

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
