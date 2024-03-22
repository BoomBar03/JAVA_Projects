package repository.book;

import model.AudioBook;
import model.Book;
import model.EBook;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    public List<Book> bookFindAll();
    public Optional<Book> bookFindById(Long id);

    public List<Book> ebookFindAll();

    public Optional<Book> ebookFindById(Long id);

    public List<Book> audiobookFindAll();

    public Optional<Book> audiobookFindById(Long id);


    boolean saveBook(Book book);

    boolean saveEBook(EBook eBook);
    boolean saveAudioBook(AudioBook audioBook);

    void bookRemoveAll();
    void ebookRemoveAll();
    void audiobookRemoveAll();

    boolean removeBook(Long id);

    boolean updateBook(Book book);

}