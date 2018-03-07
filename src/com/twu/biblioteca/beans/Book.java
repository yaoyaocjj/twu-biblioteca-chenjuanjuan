package com.twu.biblioteca.beans;

import java.util.ArrayList;

import com.twu.biblioteca.utils.CheckStatus;
import com.twu.biblioteca.utils.BookFileUtil;

public class Book {
    private String name;
    private String author;
    private String date;
    private CheckStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, String author, String date, CheckStatus status) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.status = status;
    }

    public boolean checkout() {
        BookFileUtil bookOpt = new BookFileUtil();
        return bookOpt.checkoutBook(this);
    }

    public boolean giveBack() {
        BookFileUtil bookOpt = new BookFileUtil();
        return bookOpt.returnBook(this);
    }

    public static ArrayList<Book> list() {
        BookFileUtil bookList = new BookFileUtil();
        return bookList.list();
    }

    public static Book query(String name) {
        BookFileUtil biu = new BookFileUtil();
        return biu.query(name);
    }
}
