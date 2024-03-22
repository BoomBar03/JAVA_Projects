package repository.order;

import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMySQL implements OrderRepository {
    private final Connection connection;

    public OrderRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createOrder(Order order) {
        String sql = "INSERT INTO Orders (id, employeeId, customerId, total) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, order.getEmployeeId());
            preparedStatement.setLong(3, order.getCustomerId());
            preparedStatement.setFloat(4, order.getTotal());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteOrder(Long id) {
        String sql = "DELETE FROM Orders WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        Optional<Order> order = Optional.empty();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = Optional.of(getOrderFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM Orders;";
        List<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setEmployeeId(resultSet.getLong("employeeId"));
        order.setCustomerId(resultSet.getLong("customerId"));
        order.setTotal(resultSet.getFloat("total"));
        return order;
    }

    @Override
    public void removeAll() {
        String sql = "TRUNCATE TABLE Orders;";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getLastOrderId() {
        String sql = "SELECT MAX(id) AS lastOrderId FROM Orders;";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getLong("lastOrderId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
