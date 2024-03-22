package service.order;

import model.DetailedOrder;
import repository.order.DetailedOrderRepository;
import repository.order.OrderRepository;

import java.util.List;
import java.util.Optional;

public class DetailedOrderServiceImpl implements DetailedOrderService{

    private final DetailedOrderRepository detailedOrderRepository;

    public DetailedOrderServiceImpl(DetailedOrderRepository detailedOrderRepository) {
        this.detailedOrderRepository = detailedOrderRepository;
    }

    @Override
    public boolean createDetailedOrder(DetailedOrder detailedOrder){
        return detailedOrderRepository.createDetailedOrder(detailedOrder);
    }
    @Override
    public boolean deleteDetailedOrder(Long id){
        return detailedOrderRepository.deleteDetailedOrder(id);
    }
    @Override
    public Optional<DetailedOrder> findDetailedOrderById(Long id){
        return detailedOrderRepository.findDetailedOrderById(id);
    }
    @Override
    public List<DetailedOrder> findAllDetailedOrders(){
        return detailedOrderRepository.findAllDetailedOrders();
    }
    @Override
    public void removeAllDetailedOrders(){
        detailedOrderRepository.removeAllDetailedOrders();
    }
}
