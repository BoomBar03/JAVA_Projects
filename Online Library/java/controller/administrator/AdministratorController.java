package controller.administrator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
//import controller.administrator.CreateEmployeeController;
//import controller.administrator.RetrieveEmployeeController;
//import controller.administrator.UpdateEmployeeController;
//import controller.administrator.DeleteEmployeeController;
//import controller.administrator.GeneratePdfController;
//import view.administrator.CreateEmployeeView;
//import view.administrator.RetrieveEmployeeView;
//import view.administrator.UpdateEmployeeView;
//import view.administrator.DeleteEmployeeView;
//import view.administrator.GeneratePdfView;
import model.DetailedOrder;
import model.Order;
import model.User;
import repository.order.DetailedOrderRepository;
import repository.order.DetailedOrderRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.order.DetailedOrderService;
import service.order.DetailedOrderServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.administrator.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AdministratorController {
    private final AdministratorView administratorView;

    public AdministratorController(AdministratorView administratorView) {
        this.administratorView = administratorView;
        this.administratorView.addCreateEmployeeButtonListener(new CreateEmployeeButtonListener());
        this.administratorView.addRetrieveEmployeeButtonListener(new RetrieveEmployeeButtonListener());
        this.administratorView.addUpdateEmployeeButtonListener(new UpdateEmployeeButtonListener());
        this.administratorView.addDeleteEmployeeButtonListener(new DeleteEmployeeButtonListener());
        this.administratorView.addGeneratePdfButtonListener(new GeneratePdfButtonListener());
    }
    private class CreateEmployeeButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new CreateEmployeeController(new CreateEmployeeView(new Stage()));
        }
    }

    private class RetrieveEmployeeButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new RetrieveEmployeeController(new RetrieveEmployeeView(new Stage()));
        }
    }

    private class UpdateEmployeeButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new UpdateEmployeeController(new UpdateEmployeeView(new Stage()));
        }
    }

    private class DeleteEmployeeButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new DeleteEmployeeController(new DeleteEmployeeView(new Stage()));
        }
    }

    private class GeneratePdfButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("EmployeeActivity.pdf"));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            document.open();

                PdfPTable table = new PdfPTable(3);
                addTableHeader(table);
                addRowsFromDatabase(table);

                try {
                    document.add(table);
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }

            document.close();

            administratorView.addActionTargetText("Report created successfully!");
        }
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("EmployeeId", "EmployeeUsername", "OrdersPlaced")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRowsFromDatabase(PdfPTable table) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        DetailedOrderRepository detailedOrderRepository = new DetailedOrderRepositoryMySQL(connection);
        DetailedOrderService detailedOrderService = new DetailedOrderServiceImpl(detailedOrderRepository);
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        OrderRepository orderRepository = new OrderRepositoryMySQL(connection);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        List<DetailedOrder> detailedOrders = detailedOrderService.findAllDetailedOrders();
        List<Order> orders = orderService.findAll();
        UserService userService = new UserServiceImpl(userRepository);

        List<User> allUsers = userService.findAll();
        List<User> allEmployees = new ArrayList<>();

        for(User u: allUsers){
            if(u.getRoles().get(0).getRole().equals("employee")){
                allEmployees.add(u);
            }
        }

        for (User employee : allEmployees) {
            for(Order order: orders){
                if(employee.getId().equals(order.getEmployeeId())){

                    PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(order.getEmployeeId())));
                    PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(employee.getUsername())));
                    PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(order.getId())));


                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);

                }

            }
        }

    }
}
