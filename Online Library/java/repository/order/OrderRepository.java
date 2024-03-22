package repository.order;

import model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    boolean createOrder(Order order);

    boolean deleteOrder(Long id);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    void removeAll();

    Long getLastOrderId();
}
