package controller.customer;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import view.customer.BooksView;
import view.customer.BuyBooksView;
import view.CustomerView;
import view.LoginView;

public class CustomerController {
    private final CustomerView customerView;

    private final User userLogged;

    public CustomerController(CustomerView customerView, User userLogged){
        this.customerView=customerView;
        this.userLogged=userLogged;
        this.customerView.addViewAllBooksButtonListener(new CustomerController.viewAllBooksButtonListener());
        this.customerView.addBuyBooksButtonListener(new CustomerController.buyBooksButtonListener());
    }

    private class viewAllBooksButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            new BooksView(new Stage());
        }
    }
    private class buyBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            new BuyBooksController(new BuyBooksView(new Stage(), userLogged), userLogged);
        }
    }
}
