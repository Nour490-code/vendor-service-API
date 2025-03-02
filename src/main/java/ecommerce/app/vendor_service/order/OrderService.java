package ecommerce.app.vendor_service.order;

import ecommerce.app.vendor_service.order.factory.OrderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(String address, String phoneNumber, Double totalAmount, String status) {
        OrderFactory factory = OrderFactoryProvider.getOrderFactory(status);
        Order order = factory.createOrder(address, phoneNumber, totalAmount, status);
        return orderRepository.save(order);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
