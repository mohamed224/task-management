package com.owntech.taskmanagement.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpErrorResponse {
    private int errorCode;
    private String errorResponse;

    public HttpErrorResponse(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public HttpErrorResponse(int errorCode, String errorResponse) {
        super();
        this.errorCode = errorCode;
        this.errorResponse = errorResponse;
    }

}
