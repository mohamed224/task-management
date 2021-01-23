package com.owntech.taskmanagement.exceptions;

public class ApiErrors {
    public static final int OBJECT_NOT_FOUND_STATUS_CODE = 404;
    public static final String OBJECT_NOT_FOUND_MESSAGE = "%s not found";
    public static final int BAD_CREDENTIALS_STATUS_CODE = 403;
    public static final String BAD_CREDENTIALS_MESSAGE = "Bad credentials";
    public static final int START_TASK_EXCEPTION_STATUS_CODE = 501;
    public static final String START_TASK_EXCEPTION_MESSAGE = "You cannot have more than 3 tasks in progress in the same time.";
    private ApiErrors() {
    }
}
