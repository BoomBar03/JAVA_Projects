package controller.employee;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.employee.CreateBookController;
import controller.employee.RetrieveBookController;
import controller.employee.UpdateBookController;
import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.DetailedOrder;
import model.Order;
import repository.order.DetailedOrderRepository;
import repository.order.DetailedOrderRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import service.order.DetailedOrderService;
import service.order.DetailedOrderServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import view.employee.CreateBookView;
import view.employee.DeleteBookView;
import view.employee.EmployeeView;
import view.employee.RetrieveBookView;
import view.employee.UpdateBookView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class EmployeeController {
    private final EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        this.employeeView.addCreateBookButtonListener(new CreateBookButtonListener());
        this.employeeView.addRetrieveBookButtonListener(new RetrieveBookButtonListener());
        this.employeeView.addUpdateBookButtonListener(new UpdateBookButtonListener());
        this.employeeView.addDeleteBookButtonListener(new DeleteBookButtonListener());
        this.employeeView.addCreateReportButtonListener(new CreateReportButtonListener());
    }

    private class CreateBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            new CreateBookController(new CreateBookView(new Stage()));
        }
    }

    private class RetrieveBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            new RetrieveBookController(new RetrieveBookView(new Stage()));
        }
    }

    private class UpdateBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
           new UpdateBookController(new UpdateBookView(new Stage()));
        }
    }

    private class DeleteBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            new DeleteBookController(new DeleteBookView(new Stage()));
        }
    }
    private class CreateReportButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("Orders.pdf"));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            document.open();

            PdfPTable table = new PdfPTable(6);
            addTableHeader(table);
            addRowsFromDatabase(table);

            try {
                document.add(table);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            document.close();

            employeeView.addActionTargetText("Report created successfully!");
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("OrderId", "CustomerId", "EmployeeId", "BookId", "Quantity", "OrderTotal")
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

        OrderRepository orderRepository = new OrderRepositoryMySQL(connection);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        List<DetailedOrder> detailedOrders = detailedOrderService.findAllDetailedOrders();
        List<Order> orders = orderService.findAll();

        for (Order order : orders) {
            for(DetailedOrder detailedOrder: detailedOrders){
                if(detailedOrder.getId().equals(order.getId())){
                    PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(order.getId())));
                    PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(order.getCustomerId())));
                    PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(order.getEmployeeId())));
                    PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(detailedOrder.getBookId())));
                    PdfPCell cell5 = new PdfPCell(new Phrase(String.valueOf(detailedOrder.getQuantity())));
                    PdfPCell cell6 = new PdfPCell(new Phrase(String.valueOf(order.getTotal())));

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);
                }

            }
        }

    }
}
