package controller.administrator;

import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.User;
import model.validator.Notification;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.administrator.CreateEmployeeView;
import repository.security.RightsRolesRepository;
import view.administrator.RetrieveEmployeeView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RetrieveEmployeeController {

    private final RetrieveEmployeeView retrieveEmployeeView;

    public RetrieveEmployeeController(RetrieveEmployeeView retrieveEmployeeView) {
        this.retrieveEmployeeView = retrieveEmployeeView;
        this.retrieveEmployeeView.addRetrieveEmployeeButtonListener(new RetrieveEmployeeButtonListener());
        this.retrieveEmployeeView.addRetrieveAllEmployeesButtonListener(new RetrieveAllEmployeesButtonListener());
    }

    private class RetrieveEmployeeButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            clearEmployeeTable();
            String employeeId = retrieveEmployeeView.getIdField();
            Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
            RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
            UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

            UserService userService = new UserServiceImpl(userRepository);

            User retrievedUser = userService.findById(Long.valueOf(employeeId)).getResult();

            if (retrievedUser != null) {
                if(retrievedUser.getRoles().get(0).getRole().equals("employee")){
                    displayEmployeeInTable(retrievedUser);
                    retrieveEmployeeView.setActionTargetText("Employee retrieved successfully!");

                }
                else {
                    retrieveEmployeeView.setActionTargetText("There is no employee with that ID!");
                    clearEmployeeTable();
                }

            } else {
                retrieveEmployeeView.setActionTargetText("Employee not found!");
                clearEmployeeTable();
            }
        }
    }

    private void displayEmployeeInTable(User employee) {
        User user = new User();
        user.setId(Long.valueOf(retrieveEmployeeView.getIdField()));
        user.setUsername(employee.getUsername());

        ObservableList<User> data = FXCollections.observableArrayList(user);

        retrieveEmployeeView.populateEmployeeTable(data);
    }

    private void clearEmployeeTable() {
        retrieveEmployeeView.getEmployeeTable().getItems().clear();
    }

    private class RetrieveAllEmployeesButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            clearEmployeeTable();
            String employeeId = retrieveEmployeeView.getIdField();
            Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
            RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
            UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

            UserService userService = new UserServiceImpl(userRepository);

            List<User> allUsers = userService.findAll();
            List<User> allEmployees = new ArrayList<>();

            for(User u: allUsers){
                if(u.getRoles().get(0).getRole().equals("employee")){
                    allEmployees.add(u);
                }
            }
            if (!allEmployees.isEmpty()) {
                retrieveEmployeeView.setActionTargetText("All Employees retrieved successfully!");
                displayAllEmployeesInTable(allEmployees);
            } else {
                retrieveEmployeeView.setActionTargetText("No Employees found!");
                clearEmployeeTable();
            }
        }
    }

    private void displayAllEmployeesInTable(List<User> employees) {
        ObservableList<User> data = FXCollections.observableArrayList(employees);
        retrieveEmployeeView.populateEmployeeTable(data);
    }
}
