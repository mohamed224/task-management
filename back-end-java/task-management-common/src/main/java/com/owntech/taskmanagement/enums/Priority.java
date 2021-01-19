package com.owntech.taskmanagement.enums;

public enum Priority {

    HIGH("H"),
    MEDIUM("M"),
    LOW("L"),
    ;
    private String shortName;

    Priority(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
