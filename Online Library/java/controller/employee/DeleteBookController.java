package controller.employee;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import view.employee.DeleteBookView;

public class DeleteBookController {

    private final DeleteBookView deleteBookView;

    public DeleteBookController(DeleteBookView deleteBookView) {
        this.deleteBookView = deleteBookView;
        this.deleteBookView.addDeleteBookButtonListener(new DeleteBookButtonListener());
    }

    private class DeleteBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String bookId = deleteBookView.getIdField();

            if (bookId != null && !bookId.isEmpty()) {
                Long id = Long.parseLong(bookId);

                BookRepository bookRepository = new BookRepositoryCacheDecorator(
                        new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                        new Cache<>()
                );

                BookService bookService = new BookServiceImpl(bookRepository);

                if (bookService.removeBook(id)) {
                    deleteBookView.setActionTargetText("Book deleted successfully!");
                } else {
                    deleteBookView.setActionTargetText("Book could not be deleted! Try again!");
                }
            } else {
                deleteBookView.setActionTargetText("Please enter a valid Book ID!");
            }
        }
    }
}
