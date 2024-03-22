package service.order;

import model.Order;
import repository.order.OrderRepository;
import repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public boolean createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return orderRepository.deleteOrder(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void removeAll() {
        orderRepository.removeAll();
    }

    public Long getLastOrderId(){
        return orderRepository.getLastOrderId();
    }
}
