package service.book;

import model.AudioBook;
import model.Book;
import model.EBook;
import repository.book.BookRepository;
import service.book.BookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> bookFindAll() {
        return bookRepository.bookFindAll();
    }

    public List<Book> ebookFindAll() {
        return bookRepository.ebookFindAll();
    }

    public List<Book> audiobookFindAll() {
        return bookRepository.audiobookFindAll();
    }

    public Book bookFindById(Long id) {
        return bookRepository.bookFindById(id).orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    public Book ebookFindById(Long id) {
        return bookRepository.ebookFindById(id).orElseThrow(() -> new IllegalArgumentException("EBook with id: %d not found".formatted(id)));
    }

    public Book audiobookFindById(Long id) {
        return bookRepository.audiobookFindById(id).orElseThrow(() -> new IllegalArgumentException("AudioBook with id: %d not found".formatted(id)));
    }

    @Override
    public boolean saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    public boolean saveEBook(EBook book) {
        return bookRepository.saveEBook(book);
    }
    public boolean saveAudioBook(AudioBook book) {
        return bookRepository.saveAudioBook(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = bookFindById(id);
        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }

    public void bookRemoveAll() {
        bookRepository.bookRemoveAll();
    }

    public void ebookRemoveAll() {
        bookRepository.ebookRemoveAll();
    }

    @Override
    public void audiobookRemoveAll() {
        bookRepository.audiobookRemoveAll();
    }

    public boolean removeBook(Long id){
        return bookRepository.removeBook(id);
    }

    public boolean updateBook(Book book){
        return bookRepository.updateBook(book);
    }
}
