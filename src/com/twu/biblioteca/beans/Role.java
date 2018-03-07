package com.twu.biblioteca.beans;

public enum Role {
    CUSTOMER, LIBRARIAN, NONE;

    public String toString() {
        if (this == NONE)
            return "";
        return this.name().toLowerCase();
    }

    public static Role toRole(String role) {
        if ("".equals(role))
            return NONE;
        return Role.valueOf(role.toUpperCase());
    }
}
