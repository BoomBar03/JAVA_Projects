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
import view.employee.CreateBookView;

import java.time.LocalDate;

public class CreateBookController {

    private final CreateBookView createBookView;

    public CreateBookController(CreateBookView createBookView) {
        this.createBookView = createBookView;
        this.createBookView.addCreateBookButtonListener(new CreateBookButtonListener());
    }

    private class CreateBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Book newBook = new BookBuilder<>(new Book())
                    .setAuthor(createBookView.getAuthorField())
                    .setTitle(createBookView.getTitleField())
                    .setPublishedDate(LocalDate.parse(createBookView.getPublishedDateField()))
                    .setStock(Long.valueOf(createBookView.getStockField()))
                    .setPrice(Float.valueOf(createBookView.getPriceField()))
                    .build();

            BookRepository bookRepository = new BookRepositoryCacheDecorator(
                    new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                    new Cache<>()
            );

            BookService bookService = new BookServiceImpl(bookRepository);

            if(bookService.saveBook(newBook)){
                createBookView.setActionTargetText("Book created successfully!");
            }
            else{
                createBookView.setActionTargetText("Book could not created! Try again!");
            }



        }
    }

}
