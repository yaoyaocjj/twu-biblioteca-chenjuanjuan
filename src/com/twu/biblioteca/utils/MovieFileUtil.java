package com.twu.biblioteca.utils;

import com.twu.biblioteca.beans.Movie;

import java.io.*;
import java.util.ArrayList;

public class MovieFileUtil {

    private ArrayList<Movie> allMovie = new ArrayList<Movie>();

    private boolean write(String filename) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Movie movie : allMovie) {

                bw.write(movie.getName() + " :: " + movie.getYear() + " :: " + movie.getDirector() + " :: " + movie.getRating() + " :: " + movie.getStatus().toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
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
        allMovie.clear();

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)
                allMovie.add(stringToMovie(line));
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

    private Movie stringToMovie(String content) {
        String[] strs = content.split(" :: ");
        if (strs.length != 5)
            return null;

        return new Movie(strs[0], Integer.parseInt(strs[1]), strs[2], Integer.parseInt(strs[3]), CheckStatus.toStatus(strs[4]));
    }

    public Movie query(String name) {
        if (!queryAll(Constant.MOVIES_FILE))
            return null;

        for (Movie b : allMovie
                ) {
            if (b.getName().equals(name))
                return b;
        }
        return null;
    }

    public ArrayList<Movie> list() {

        if (!queryAll(Constant.MOVIES_FILE))
            return null;

        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (Movie b : allMovie) {
            if (b.getStatus().equals(CheckStatus.UNCHECKOUT))
                movies.add(b);
        }
        return movies;
    }

    public boolean checkoutMovie(Movie movie) {
        return changeStatus(movie, CheckStatus.CHECKOUT);
    }

    public boolean returnMovie(Movie movie) {
        return changeStatus(movie, CheckStatus.UNCHECKOUT);
    }

    private boolean changeStatus(Movie movie, CheckStatus status) {

        if (!queryAll(Constant.MOVIES_FILE))
            return false;

        for (Movie b : allMovie) {
            if (b.getName().equals(movie.getName()))
                movie = b;
        }

        if (!movie.getStatus().equals(status)) {
            allMovie.remove(movie);
            movie.setStatus(status);
            allMovie.add(movie);
            if (write(Constant.MOVIES_FILE))
                return true;
        }

        return false;
    }
}
