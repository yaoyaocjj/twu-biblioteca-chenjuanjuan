package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.twu.biblioteca.beans.User;
import com.twu.biblioteca.beans.Book;
import com.twu.biblioteca.beans.Movie;
import com.twu.biblioteca.utils.CheckStatus;

public class BibliotecaTest {
    User user =new User("userTest", "111111");
    Book book =new Book("bookTest", "test", "2018-03-07", CheckStatus.CHECKOUT);
    Movie movie =new Movie("movieTest", 2017, "test", 10, CheckStatus.UNCHECKOUT);

    @Test
    public void testUser() {
        user.setTel("1234567890");
        assertEquals(user.getUsername(), "userTest");
        assertEquals(user.getPassword(), "111111");
        assertEquals(user.getTel(), "1234567890");
    }

    @Test
    public void testBook() {
        assertEquals(book.getName(), "bookTest");
        assertEquals(book.getAuthor(), "test");
        assertEquals(book.getDate(), "2018-03-07");
        book.setDate("2020-03-08");
        assertEquals(book.getDate(), "2020-03-08");
        assertEquals(book.getStatus(), CheckStatus.CHECKOUT);
    }

    @Test
    public void testMovie() {
        assertEquals(movie.getName(), "movieTest");
        assertEquals(movie.getDirector(), "test");
        assertEquals(movie.getRating(), 10);
        assertEquals(movie.getStatus(), CheckStatus.UNCHECKOUT);
    }

}
