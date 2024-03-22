package repository.book;

import repository.book.BookRepository;

public abstract class BookRepositoryDecorator implements BookRepository {

    protected BookRepository decoratedRepository;

    public BookRepositoryDecorator(BookRepository bookRepository){
        this.decoratedRepository = bookRepository;
    }
}