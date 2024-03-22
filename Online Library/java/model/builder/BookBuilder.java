package model.builder;

import java.time.LocalDate;

import model.AudioBook;
import model.Book;
import model.EBook;

public class BookBuilder<T extends Book> {
    private T book;

    public BookBuilder(T book) {
        this.book = book;
    }


    public BookBuilder<T> setId(Long id) {
        book.setId(id);
        return this;
    }

    public BookBuilder<T> setAuthor(String author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder<T> setTitle(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder<T> setPublishedDate(LocalDate publishedDate) {
        book.setPublishedDate(publishedDate);
        return this;
    }

    public BookBuilder<T> setStock(Long stock) {
        book.setStock(stock);
        return this;
    }

    public BookBuilder<T> setPrice(Float price) {
        book.setPrice(price);
        return this;
    }

    public BookBuilder<T> setRunTime(int runTime) {
        if (book instanceof AudioBook) {
            ((AudioBook) book).setRunTime(runTime);
        }
        return this;
    }

    public BookBuilder<T> setFormat(String format) {
        if (book instanceof EBook) {
            ((EBook) book).setFormat(format);
        }
        return this;
    }

    public T build() {
        return book;
    }
}
