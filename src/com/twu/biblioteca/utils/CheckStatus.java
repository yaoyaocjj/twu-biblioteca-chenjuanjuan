package com.twu.biblioteca.utils;

public enum CheckStatus {
    UNCHECKOUT, CHECKOUT, NONE;

    public String toString() {
        if(this == NONE)
            return "";
        return this.name().toLowerCase();
    }

    public static CheckStatus toStatus(String status) {
        if("".equals(status))
            return NONE;
        return CheckStatus.valueOf(status.toUpperCase());
    }
}
