package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

import com.twu.biblioteca.beans.Book;
import com.twu.biblioteca.beans.User;
import com.twu.biblioteca.beans.Movie;

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
        System.out.println("***********************");
        System.out.println("Welcome to Biblioteca!");
        System.out.println("***********************");
        System.out.println();
    }

    private static int mainMenu() {
        System.out.println("-----Book Option-----");
        System.out.println("1. Books List.");
        System.out.println("2. Book Details.");
        System.out.println("3. Checkout Book.");
        System.out.println("4. Return Book.");
        System.out.println();
        System.out.println("-----Movie Option-----");
        System.out.println("5. Movies List.");
        System.out.println("6. Movie Details.");
        System.out.println("7. Checkout Movie.");
        System.out.println("8. Return Movie.");
        System.out.println();
        System.out.println("-----user info-----");
        System.out.println("9. Personal Information.");
        System.out.println();
        System.out.println("-----Quit-----");
        System.out.println("0. Quit.");
        System.out.print("Please input your option: ");

        int opt = in.nextInt();
        in.nextLine();
        return opt;
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
        System.out.println("The Books list:");
        for (Book book : books) {
            System.out.println(book.getName());
        }
        System.out.println();
    }

    private static void queryBook() {
        System.out.print("Please input the name of book: ");
        Book book = Book.query(in.nextLine());
        if (book == null)
            System.out.println("The book doesn't exist.");
        else
            System.out.println(book.getName() + " :: " + book.getAuthor() + " :: " + book.getDate());
    }

    private static void checkoutBook() {
        System.out.print("Please input the name of book: ");
        Book book = Book.query(in.nextLine());
        if (book == null)
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
        if (book == null)
            System.out.println("The book doesn't exist.");
        else {
            if (book.giveBack())
                System.out.println("Thank you for returning the book.");
            else
                System.out.println("That is not a valid book to return.");
        }
    }

    private static void listMovies() {
        ArrayList<Movie> movies = Movie.list();
        System.out.println();
        System.out.println("The Movies list: ");
        for (Movie movie : movies
                ) {
            System.out.println(movie.getName());
        }
    }

    private static void queryMovie() {
        System.out.print("Please input the name of movie: ");
        Movie movie = Movie.query(in.nextLine());
        if (movie == null)
            System.out.println("The movie doesn't exist.");
        else
            System.out.println(movie.getName() + " :: " + movie.getYear() + " :: " + movie.getDirector() + " :: " + movie.getRating());
    }


    private static void checkoutMovie() {
        System.out.print("Please input the name of movie: ");
        Movie movie = Movie.query(in.nextLine());
        if (movie == null)
            System.out.println("The movie doesn't exist.");
        else {
            if (movie.checkoutMovie())
                System.out.println("Thank you! Enjoy the movie!");
            else
                System.out.println("That movie is not available.");
        }
    }

    private static void returnMovie() {
        System.out.print("Please input the name of movie: ");
        Movie movie = Movie.query(in.nextLine());
        if (movie == null)
            System.out.println("The movie doesn't exist.");
        else {
            if (movie.returnMovie())
                System.out.println("Thank you for returning the movie.");
            else
                System.out.println("That is not a valid movie to return.");
        }
    }


    private static void showInfo() {
        System.out.println("Name :: Role :: Email :: Tel");
        System.out.println(user.toString());
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
                if (login())
                    checkoutBook();
                break;
            case 4:
                if (login())
                    returnBook();
                break;
            case 5:
                listMovies();
                break;
            case 6:
                queryMovie();
                break;
            case 7:
                if (login())
                    checkoutMovie();
                break;
            case 8:
                if (login())
                    returnMovie();
                break;
            case 9:
                if (login())
                    showInfo();
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
        while (opt != 0) {
            opt = mainMenu();
            option(opt);
        }
    }
}
