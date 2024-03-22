package repository.book;

import model.AudioBook;
import model.Book;
import model.EBook;
import repository.book.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository {
    private List<Book> books;

    public BookRepositoryMock(){
        books = new ArrayList<>();
    }

    @Override
    public List<Book> bookFindAll() {
        return books;
    }

    public List<Book> ebookFindAll() {
        return books;
    }

    public List<Book> audiobookFindAll() {
        return books;
    }

    @Override
    public Optional<Book> bookFindById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    public Optional<Book> ebookFindById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    public Optional<Book> audiobookFindById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }


    public boolean saveBook(Book book) {
        return books.add(book);
    }

    public boolean saveEBook(EBook book) {
        return books.add(book);
    }

    public boolean saveAudioBook(AudioBook book) {
        return books.add(book);
    }

    public void bookRemoveAll() {
        books.clear();
    }

    public void ebookRemoveAll() {
        books.clear();
    }

    public void audiobookRemoveAll() {
        books.clear();
    }

    public boolean removeBook(Long id){
        return books.remove(id);
    }

    public boolean updateBook(Book book){
        return updateBook(book);
    }
}