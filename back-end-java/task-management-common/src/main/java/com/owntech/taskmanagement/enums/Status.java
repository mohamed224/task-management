package com.owntech.taskmanagement.enums;

public enum Status {
    TODO("T"),
    IN_PROGRESS("P"),
    DONE("D"),
    ;

    private String shortName;

    Status(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
