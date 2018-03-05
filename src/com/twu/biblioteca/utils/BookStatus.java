package com.twu.biblioteca.utils;

public enum  BookStatus {
    UNCHECKOUT, CHECKOUT, NONE;

    public String toString() {
        if(this == NONE)
            return "";
        return this.name().toLowerCase();
    }

    public static BookStatus toStatus(String status) {
        if("".equals(status))
            return NONE;
        return BookStatus.valueOf(status.toUpperCase());
    }
}
