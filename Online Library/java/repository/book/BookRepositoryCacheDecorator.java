package repository.book;

import model.AudioBook;
import model.Book;
import model.EBook;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator {
    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache) {
        super(bookRepository);
        this.cache = cache;
    }

    @Override
    public List<Book> bookFindAll() {
        if (cache.hasResult()) {
            return cache.load();
        }

        List<Book> books = decoratedRepository.bookFindAll();
        cache.save(books);

        return books;
    }

    public List<Book> ebookFindAll() {
        if (cache.hasResult()) {
            return cache.load();
        }

        List<Book> books = decoratedRepository.ebookFindAll();
        cache.save(books);

        return books;
    }

    public List<Book> audiobookFindAll() {
        if (cache.hasResult()) {
            return cache.load();
        }

        List<Book> books = decoratedRepository.audiobookFindAll();
        cache.save(books);

        return books;
    }


    public Optional<Book> bookFindById(Long id) {
        if (cache.hasResult()) {
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        Optional<Book> book = decoratedRepository.bookFindById(id);

        return book;
    }

    public Optional<Book> ebookFindById(Long id) {
        if (cache.hasResult()) {
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        Optional<Book> book = decoratedRepository.ebookFindById(id);

        return book;
    }

    public Optional<Book> audiobookFindById(Long id) {
        if (cache.hasResult()) {
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        Optional<Book> book = decoratedRepository.audiobookFindById(id);

        return book;
    }

    @Override
    public boolean saveBook(Book book) {
        cache.invalidateCache();
        return decoratedRepository.saveBook(book);
    }

    public boolean saveEBook(EBook book) {
        cache.invalidateCache();
        return decoratedRepository.saveEBook(book);
    }

    public boolean saveAudioBook(AudioBook book) {
        cache.invalidateCache();
        return decoratedRepository.saveAudioBook(book);
    }

    @Override
    public void bookRemoveAll() {
        cache.invalidateCache();
        decoratedRepository.bookRemoveAll();
    }

    public void ebookRemoveAll() {
        cache.invalidateCache();
        decoratedRepository.ebookRemoveAll();
    }

    public void audiobookRemoveAll() {
        cache.invalidateCache();
        decoratedRepository.audiobookRemoveAll();
    }

    public boolean removeBook(Long id) {
        cache.invalidateCache();
        return decoratedRepository.removeBook(id);
    }

    public boolean updateBook(Book book){
        cache.invalidateCache();
        return decoratedRepository.updateBook(book);
    }

}