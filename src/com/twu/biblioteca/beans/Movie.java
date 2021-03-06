package com.twu.biblioteca.beans;

import java.util.ArrayList;

import com.twu.biblioteca.utils.CheckStatus;
import com.twu.biblioteca.utils.MovieFileUtil;

public class Movie {

    private int id = 0;

    private String name;
    private int year;
    private String director;
    private int rating = 0;
    private CheckStatus status;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, int year, String director, int rating, CheckStatus status) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public static ArrayList<Movie> list() {
        MovieFileUtil movie = new MovieFileUtil();
        return movie.list();
    }

    public static Movie query(String name) {
        MovieFileUtil movie = new MovieFileUtil();
        return movie.query(name);
    }

    public boolean checkoutMovie() {
        MovieFileUtil movie = new MovieFileUtil();
        return movie.checkoutMovie(this);
    }

    public boolean returnMovie() {
        MovieFileUtil movie = new MovieFileUtil();
        return movie.returnMovie(this);
    }
}
