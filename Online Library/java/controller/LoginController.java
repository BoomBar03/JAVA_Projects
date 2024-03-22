package controller;

import controller.administrator.AdministratorController;
import controller.customer.CustomerController;
import controller.employee.EmployeeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import service.user.AuthenticationService;
import view.CustomerView;
import view.LoginView;
import view.administrator.AdministratorView;
import view.employee.EmployeeView;

import java.util.EventListener;
import java.util.List;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;


    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                System.out.println(loginNotification.getResult().getRoles().get(0));
                if(loginNotification.getResult().getRoles().get(0).getRole().equals("administrator")){
                    new AdministratorController(new AdministratorView(new Stage()));
                }
                else if(loginNotification.getResult().getRoles().get(0).getRole().equals("employee")){
                    new EmployeeController(new EmployeeView(new Stage()));
                }
                else if(loginNotification.getResult().getRoles().get(0).getRole().equals("customer")){
                    new CustomerController(new CustomerView(new Stage()), loginNotification.getResult());
                }
            }

        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}