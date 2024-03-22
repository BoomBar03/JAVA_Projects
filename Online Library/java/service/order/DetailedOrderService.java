package service.order;

import model.DetailedOrder;

import java.util.List;
import java.util.Optional;

public interface DetailedOrderService {
    boolean createDetailedOrder(DetailedOrder detailedOrder);
    boolean deleteDetailedOrder(Long id);

    Optional<DetailedOrder> findDetailedOrderById(Long id);

    List<DetailedOrder> findAllDetailedOrders();

    void removeAllDetailedOrders();
}
