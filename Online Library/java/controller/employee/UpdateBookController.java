package controller.employee;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import view.employee.UpdateBookView;

import java.time.LocalDate;

public class UpdateBookController {

    private final UpdateBookView updateBookView;

    public UpdateBookController(UpdateBookView updateBookView) {
        this.updateBookView = updateBookView;
        this.updateBookView.addUpdateBookButtonListener(new UpdateBookButtonListener());
    }

    private class UpdateBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            if(!updateBookView.getIdField().equals("")) {
                BookRepository bookRepository = new BookRepositoryCacheDecorator(
                        new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                        new Cache<>()
                );

                BookService bookService = new BookServiceImpl(bookRepository);
                Book bookToBeUpdated = bookService.bookFindById(Long.valueOf(updateBookView.getIdField()));

                Book updatedBook = new BookBuilder<>(new Book())
                        .setId(Long.valueOf(updateBookView.getIdField()))
                        .build();

                if (updateBookView.getAuthorField().equals("")) {
                    updatedBook.setAuthor(bookToBeUpdated.getAuthor());
                } else {
                    updatedBook.setAuthor(updateBookView.getAuthorField());
                }

                if (updateBookView.getTitleField().equals("")) {
                    updatedBook.setTitle(bookToBeUpdated.getTitle());
                } else {
                    updatedBook.setTitle(updateBookView.getTitleField());
                }

                if (updateBookView.getPublishedDateField().equals("")) {
                    updatedBook.setPublishedDate(bookToBeUpdated.getPublishedDate());
                } else {
                    updatedBook.setPublishedDate(LocalDate.parse(updateBookView.getPublishedDateField()));
                }

                if (updateBookView.getStockField().equals("")) {
                    updatedBook.setStock(bookToBeUpdated.getStock());
                } else {
                    updatedBook.setStock(Long.valueOf(updateBookView.getStockField()));
                }

                if (updateBookView.getPriceField().equals("")) {
                    updatedBook.setPrice(bookToBeUpdated.getPrice());
                } else {
                    updatedBook.setPrice(Float.valueOf(updateBookView.getPriceField()));
                }

                if (bookService.updateBook(updatedBook)) {
                    updateBookView.setActionTargetText("Book updated successfully!");
                } else {
                    updateBookView.setActionTargetText("Book could not be updated! Try again!");
                }
            }
            else{
                updateBookView.setActionTargetText("An ID must be provided in order to update a book!");
            }
        }
    }
}
