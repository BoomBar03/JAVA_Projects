package controller.administrator;
import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Role;
import model.User;
import model.validator.Notification;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.administrator.CreateEmployeeView;
import repository.security.RightsRolesRepository;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeController {
    private final CreateEmployeeView createEmployeeView;


    public CreateEmployeeController(CreateEmployeeView createEmployeeView) {
        this.createEmployeeView = createEmployeeView;
        this.createEmployeeView.addCreateEmployeeButtonListener(new CreateEmployeeButtonListener());
    }

    private class CreateEmployeeButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            String username = createEmployeeView.getUsernameField();
            String password = createEmployeeView.getPasswordField();

            Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
            RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
            UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

            UserService userService = new UserServiceImpl(userRepository);

            User newEmployee = new User();
            newEmployee.setUsername(username);
            newEmployee.setPassword(password);
            List<Role> employeRole = new ArrayList<>();
            Role newRole = new Role(2L, "employee", new ArrayList<>());
            employeRole.add(newRole);
            newEmployee.setRoles(employeRole);

            if (userService.save(newEmployee)) {
                createEmployeeView.setActionTargetText("Employee created!");
            } else {
                createEmployeeView.setActionTargetText("Employee could not be created!");
            }
        }
    }
}
