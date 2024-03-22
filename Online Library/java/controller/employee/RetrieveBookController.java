package controller.employee;

import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import view.employee.RetrieveBookView;

import java.util.List;

public class RetrieveBookController {

    private final RetrieveBookView retrieveBookView;

    public RetrieveBookController(RetrieveBookView retrieveBookView) {
        this.retrieveBookView = retrieveBookView;
        this.retrieveBookView.addRetrieveBookButtonListener(new RetrieveBookButtonListener());
    }

    private class RetrieveBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String bookId = retrieveBookView.getIdField();
            BookRepository bookRepository = new BookRepositoryCacheDecorator(
                    new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                    new Cache<>()
            );

            BookService bookService = new BookServiceImpl(bookRepository);

            Book retrievedBook = bookService.bookFindById(Long.valueOf(bookId));
            if (retrievedBook != null) {
                displayBookInTable(retrievedBook);
                retrieveBookView.setActionTargetText("Book retrieved successfully!");
            } else {
                retrieveBookView.setActionTargetText("Book not found!");
                clearBookTable();
            }
        }
    }

    private void displayBookInTable(Book book) {
        ObservableList<Book> data = FXCollections.observableArrayList(
                new BookBuilder<>(new Book())
                        .setId(Long.valueOf(retrieveBookView.getIdField()))
                        .setAuthor(book.getAuthor())
                        .setTitle(book.getTitle())
                        .setPublishedDate(book.getPublishedDate())
                        .setStock(book.getStock())
                        .setPrice(book.getPrice())
                        .build()
        );

        retrieveBookView.populateBookTable(data);
    }

    private void clearBookTable() {
        retrieveBookView.getBookTable().getItems().clear();
    }
}
