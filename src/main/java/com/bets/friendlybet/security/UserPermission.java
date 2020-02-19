package com.bets.friendlybet.security;

public enum UserPermission {
    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    COURSE_READ("course_read"),
    COURSE_WRITE("course_write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
