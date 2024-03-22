package controller.customer;

import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.DetailedOrder;
import model.Order;
import model.User;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.order.DetailedOrderRepository;
import repository.order.DetailedOrderRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.order.DetailedOrderService;
import service.order.DetailedOrderServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.customer.BuyBooksView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuyBooksController {

    private BuyBooksView buyBooksView;

    private User userLogged;

    private static Long orderIdCounter = 0L;


    public BuyBooksController(BuyBooksView buyBooksView, User userLogged) {
        this.buyBooksView = buyBooksView;
        this.userLogged=userLogged;
        this.buyBooksView.addToCartButtonListener(new addToCartButtonListener());
        this.buyBooksView.placeOrderButtonListener(new placeOrderButtonListener());
    }

    private class addToCartButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                    new Cache<>()
            );
            BookService bookService = new BookServiceImpl(bookRepository);

            Book selectedBook = bookService.bookFindById(Long.valueOf(buyBooksView.getBookIdField().getText()));

            if (selectedBook != null) {
                String quantityText = buyBooksView.getQuantityField().getText();
                if (!quantityText.matches("\\d+")) {
                    return;
                }

                int quantity = Integer.parseInt(quantityText);

                if (quantity > selectedBook.getStock()) {
                    showAlert("Error", "Quantity requested is greater than available stock.");
                    return;
                }


                selectedBook.setStock(selectedBook.getStock() - Integer.parseInt(buyBooksView.getQuantityField().getText()));
                bookService.updateBook(selectedBook);


                Book orderedBook = new BookBuilder<>(selectedBook)
                        .setStock((long) quantity)
                        .setPrice(selectedBook.getPrice())
                        .build();

                buyBooksView.getCartTable().getItems().add(orderedBook);
                buyBooksView.getQuantityField().clear();

                buyBooksView.getBooksTable().getItems().setAll(bookService.bookFindAll());
                buyBooksView.getBooksTable().refresh();
            }
        }
    }

    private class placeOrderButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();

            OrderRepository orderRepository = new OrderRepositoryMySQL(connection);
            OrderService orderService = new OrderServiceImpl(orderRepository);

            DetailedOrderRepository detailedOrderRepository = new DetailedOrderRepositoryMySQL(connection);
            DetailedOrderService detailedOrderService = new DetailedOrderServiceImpl(detailedOrderRepository);

            RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
            UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

            UserService userService = new UserServiceImpl(userRepository);

            Long lastOrderId = orderRepository.getLastOrderId();
            Long orderIdCounter = (lastOrderId != null) ? lastOrderId + 1 : 1;

            List<User> allUsers = userService.findAll();
            List<User> allEmployees = new ArrayList<>();

            for(User u: allUsers){
                if(u.getRoles().get(0).getRole().equals("employee")){
                    allEmployees.add(u);
                }
            }

            float total = calculateTotal(buyBooksView.getCartTable().getItems());

            Order order = new Order();
            order.setId(orderIdCounter);
            order.setCustomerId(userLogged.getId());

            Random random = new Random();
            int randomIndex = random.nextInt(allEmployees.size());
            User randomlySelectedEmployee = allEmployees.get(randomIndex);
            order.setEmployeeId(randomlySelectedEmployee.getId());
            order.setTotal(total);
            orderService.createOrder(order);

            for (Book book : buyBooksView.getCartTable().getItems()) {
                DetailedOrder detailedOrder = new DetailedOrder();
                detailedOrder.setId(order.getId());
                detailedOrder.setBookId(book.getId());
                detailedOrder.setQuantity(book.getStock());
                detailedOrder.setPrice(book.getPrice());

                detailedOrderService.createDetailedOrder(detailedOrder);
            }
            buyBooksView.getCartTable().getItems().clear();
            buyBooksView.setActionTargetText("Order placed successfully!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void populateBookIdTextField() {

        buyBooksView.setCartTable();
    }

    private float calculateTotal(List<Book> books) {
        float total = 0;

        for (Book book : books) {
            total += book.getPrice();
        }

        return total;
    }

}
