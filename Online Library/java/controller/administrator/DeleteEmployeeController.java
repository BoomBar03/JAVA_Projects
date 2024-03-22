package controller.administrator;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.validator.Notification;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.administrator.DeleteEmployeeView;

import java.sql.Connection;

public class DeleteEmployeeController {
    private final DeleteEmployeeView deleteEmployeeView;

    public DeleteEmployeeController(DeleteEmployeeView deleteEmployeeView) {
        this.deleteEmployeeView = deleteEmployeeView;
        this.deleteEmployeeView.addDeleteEmployeeButtonListener(new DeleteEmployeeButtonListener());
    }

    private class DeleteEmployeeButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String employeeId = deleteEmployeeView.getIdField();

            try {
                Long id = Long.parseLong(employeeId);
                Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
                UserRepository userRepository = new UserRepositoryMySQL(connection, new RightsRolesRepositoryMySQL(connection));
                UserService userService = new UserServiceImpl(userRepository);


                if (!userService.removeById(id)) {
                    deleteEmployeeView.setActionTargetText("Error deleting employee");
                } else {
                    deleteEmployeeView.setActionTargetText("Employee deleted successfully!");
                }
            } catch (NumberFormatException e) {
                deleteEmployeeView.setActionTargetText("Invalid employee ID. Please enter a valid number.");
            }
        }
    }
}
