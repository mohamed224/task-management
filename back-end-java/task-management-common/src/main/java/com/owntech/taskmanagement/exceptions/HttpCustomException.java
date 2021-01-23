package com.owntech.taskmanagement.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpCustomException extends RuntimeException {
    private final int errorCode;
    private String errorResponse;

    public HttpCustomException(int errorCode) {
        super();
        this.errorCode = errorCode;

    }

    public HttpCustomException(int errorCode, String errorResponse) {
        super();
        this.errorCode = errorCode;
        this.errorResponse = errorResponse;
    }


}
