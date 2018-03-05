package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

import com.twu.biblioteca.beans.Book;
import com.twu.biblioteca.beans.User;

public class BibliotecaApp {

    private static Scanner in = new Scanner(System.in);
    private static User user = null;

    private static User inputUser() {

        String username = "", password = "";

        System.out.print("Please input your username: ");
        username = in.nextLine();
        System.out.print("Please input your password: ");
        password = in.nextLine();
        System.out.println(/*"---" + username + "-" + password + "---"*/);
        return new User(username, password);
    }

    private static void getWelcomeInfo() {

        System.out.println("Welcome to Biblioteca!");
        System.out.println();
    }

    private static int mainMenu() {
        clearScreen();
        System.out.println("---------------");
        System.out.println("1. Books List.");
        System.out.println("2. Book Details.");
        System.out.println("3. Checkout Book.");
        System.out.println("4. Return Book.");
        System.out.println();
        System.out.println("0. Quit.");
        System.out.println("---------------");
        System.out.print("Please input your choise: ");

        int opt = in.nextInt();
        in.nextLine();
        return opt;
    }

    private static void clearScreen() {
        for(int i = 0; i < 2; i++)
            System.out.println();
    }

    private static boolean login() {
        if (user != null) return true;
        System.out.println("Please login.");
        user = inputUser();

        if (!user.login()) {
            System.out.println("Sorry! Your username or password is wrong!");
            user = null;
            return false;
        }
        System.out.println("Hello, " + user.getUsername() + "! ");
        return true;

    }

    private static void listBooks() {
        ArrayList<Book> books = Book.list();
        System.out.println();
        System.out.println("Name :: Author :: Date");
        for (Book book:books
                ) {
            System.out.println(book.getName() + " :: " + book.getAuthor() + " :: " + book.getDate());
        }
        System.out.println();
    }

    private static void queryBook() {
        System.out.print("Please input the name of book: ");
        Book book = Book.query(in.nextLine());
        if(book == null)
            System.out.println("The book doesn't exist.");
        else
            System.out.println(book.getName() + " :: " + book.getAuthor() + " :: " + book.getDate());
    }

    private static void checkoutBook() {
        System.out.print("Please input the name of book: ");
        Book book = Book.query(in.nextLine());
        if(book == null)
            System.out.println("The book doesn't exist.");
        else {
            if (book.checkout())
                System.out.println("Thank you! Enjoy the book!");
            else
                System.out.println("That book is not available.");
        }
    }

    private static void returnBook() {
        System.out.print("Please input the name of book: ");
        Book book = Book.query(in.nextLine());
        if(book == null)
            System.out.println("The book doesn't exist.");
        else {
            if (book.giveBack())
                System.out.println("Thank you for returning the book.");
            else
                System.out.println("That is not a valid book to return.");
        }
    }


    private static void option(int opt) {
        switch (opt) {
            case 1:
                listBooks();
                break;
            case 2:
                queryBook();
                break;
            case 3:
                if(login())
                    checkoutBook();
                break;
            case 4:
                if(login())
                    returnBook();
                break;
            case 0:
                System.out.println("Quit!");
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    public static void main(String[] args) {
        getWelcomeInfo();

        int opt = -1;
        while(opt != 0) {
            opt = mainMenu();
            option(opt);
        }
    }
}
