package com.twu.biblioteca.utils;

import com.twu.biblioteca.beans.Book;

import java.io.*;
import java.util.ArrayList;

public class BookFileUtil {

    private ArrayList<Book> allBook = new ArrayList<Book>();

    private boolean write(String filename) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Book book: allBook) {

                bw.write(book.getName() + " :: " + book.getAuthor() + " :: " + book.getDate() + " :: " + book.getStatus().toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private boolean queryAll(String filename) {
        BufferedReader br = null;
        String line;
        allBook.clear();

        try {
            br = new BufferedReader(new FileReader(filename));
            while((line = br.readLine()) != null)
                allBook.add(stringToBook(line));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                return false;
            }
        }
    }

    private Book stringToBook(String content) {
        String[] strs = content.split(" :: ");
        if(strs.length != 4)
            return null;

        return new Book(strs[0], strs[1], strs[2], CheckStatus.toStatus(strs[3]));
    }


    public Book query(String name) {
        if(!queryAll(Constant.BOOKS_FILE))
            return null;

        for (Book b: allBook
                ) {
            if(b.getName().equals(name))
                return b;
        }
        return null;
    }

    private boolean changeStatus(Book book, CheckStatus status) {

        if(!queryAll(Constant.BOOKS_FILE))
            return false;

        for (Book b: allBook) {
            if(b.getName().equals(book.getName()))
                book = b;
        }

        if(!book.getStatus().equals(status)) {
            allBook.remove(book);
            book.setStatus(status);
            allBook.add(book);
            if(write(Constant.BOOKS_FILE))
                return true;
        }

        return false;
    }

    public boolean checkoutBook(Book book) {
        return changeStatus(book, CheckStatus.CHECKOUT);
    }

    public boolean returnBook(Book book) {
        return changeStatus(book, CheckStatus.UNCHECKOUT);
    }

    public ArrayList<Book> list() {

        if(!queryAll(Constant.BOOKS_FILE))
            return null;

        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b: allBook) {
            if(b.getStatus().equals(CheckStatus.UNCHECKOUT))
                books.add(b);
        }
        return books;
    }
}
