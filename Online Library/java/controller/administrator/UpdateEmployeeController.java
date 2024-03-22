package controller.administrator;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.User;
import model.validator.Notification;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.administrator.UpdateEmployeeView;

import java.sql.Connection;

public class UpdateEmployeeController {
    private final UpdateEmployeeView updateEmployeeView;

    public UpdateEmployeeController(UpdateEmployeeView updateEmployeeView) {
        this.updateEmployeeView = updateEmployeeView;
        this.updateEmployeeView.addUpdateEmployeeButtonListener(new UpdateEmployeeButtonListener());
    }

    private class UpdateEmployeeButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String idString = updateEmployeeView.getIdField();
            String newUsername = updateEmployeeView.getUsernameField();
            String newPassword = updateEmployeeView.getPasswordField();

            try {
                long id = Long.parseLong(idString);

                Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
                RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
                UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

                UserService userService = new UserServiceImpl(userRepository);

                Notification<User> userNotification = userService.findById(id);

                if (userNotification.hasErrors()) {
                    if(!userNotification.getResult().getRoles().get(0).getRole().equals("employee")){
                        updateEmployeeView.setActionTargetText("The user selected is not an employee!");
                    }
                    else{
                        updateEmployeeView.setActionTargetText("Employee not found!");
                    }
                } else {
                    User existingEmployee = userNotification.getResult();
                    if(newUsername!=null && newUsername!=""){
                        existingEmployee.setUsername(newUsername);
                    }
                    if(newPassword!=null && newPassword!=""){
                        existingEmployee.setPassword(newPassword);
                    }

                    if (userService.updateUser(existingEmployee)) {
                        updateEmployeeView.setActionTargetText("Employee updated successfully!");
                    } else {
                        updateEmployeeView.setActionTargetText("Employee could not be updated!");
                    }
                }
            } catch (NumberFormatException e) {
                updateEmployeeView.setActionTargetText("Invalid ID format!");
            }
        }
    }
}
