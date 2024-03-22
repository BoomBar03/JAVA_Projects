package service.book;

import model.AudioBook;
import model.Book;
import model.EBook;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<Book> bookFindAll();
    public Book bookFindById(Long id);

    public List<Book> ebookFindAll();

    public Book ebookFindById(Long id);

    public List<Book> audiobookFindAll();

    public Book audiobookFindById(Long id);


    boolean saveBook(Book book);
    boolean saveEBook(EBook book);
    boolean saveAudioBook(AudioBook book);

    void bookRemoveAll();
    void ebookRemoveAll();
    void audiobookRemoveAll();

    int getAgeOfBook(Long id);
    boolean removeBook(Long id);

    boolean updateBook(Book book);
}