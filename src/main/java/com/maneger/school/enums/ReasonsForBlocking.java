package com.maneger.school.enums;

public enum ReasonsForBlocking {

    BLOCKED_1("User deactivated due to 3 unsuccessful login attempts"),
    BLOCKED_2("SUSPICION FRAUD -- User deactivated for admin -- SUSPICION FRAUD");

    private final String description;

    ReasonsForBlocking(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
