package repository.order;

import model.DetailedOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailedOrderRepositoryMySQL implements DetailedOrderRepository {
    private final Connection connection;

    public DetailedOrderRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createDetailedOrder(DetailedOrder detailedOrder) {
        String sql = "INSERT INTO detailed_orders (id, bookId, quantity, price) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, detailedOrder.getId());
            preparedStatement.setLong(2, detailedOrder.getBookId());
            preparedStatement.setLong(3, detailedOrder.getQuantity());
            preparedStatement.setFloat(4, detailedOrder.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDetailedOrder(Long id) {
        String sql = "DELETE FROM detailed_orders WHERE id = ?;";

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
    public Optional<DetailedOrder> findDetailedOrderById(Long id) {
        String sql = "SELECT * FROM detailed_orders WHERE id = ?";
        Optional<DetailedOrder> detailedOrder = Optional.empty();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                detailedOrder = Optional.of(getDetailedOrderFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailedOrder;
    }

    @Override
    public List<DetailedOrder> findAllDetailedOrders() {
        String sql = "SELECT * FROM detailed_orders;";
        List<DetailedOrder> detailedOrders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                detailedOrders.add(getDetailedOrderFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailedOrders;
    }

    private DetailedOrder getDetailedOrderFromResultSet(ResultSet resultSet) throws SQLException {
        DetailedOrder detailedOrder = new DetailedOrder();
        detailedOrder.setId(resultSet.getLong("id"));
        detailedOrder.setBookId(resultSet.getLong("bookId"));
        detailedOrder.setQuantity(resultSet.getLong("quantity"));
        detailedOrder.setPrice(resultSet.getFloat("price"));
        return detailedOrder;
    }

    @Override
    public void removeAllDetailedOrders() {
        String sql = "TRUNCATE TABLE detailed_orders;";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
