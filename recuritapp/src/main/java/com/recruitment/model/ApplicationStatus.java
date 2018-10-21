package com.recruitment.model;

public enum ApplicationStatus {
    APPLIED(0), INVITED(1), REJECTED(2), HIRED(3);

    private final int value;

    public int getValue() {
        return value;
    }

    ApplicationStatus(final int value) {
        this.value = value;
    }
}
