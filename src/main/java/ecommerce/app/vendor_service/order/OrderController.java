package ecommerce.app.vendor_service.order;

import ecommerce.app.vendor_service.order.Order;
import ecommerce.app.vendor_service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/")
    public Order createOrder(@RequestBody Map<String,String> payload) {
        return orderService.createOrder(payload.get("address"), payload.get("phoneNumber"), Double.parseDouble(payload.get("totalAmount")), payload.get("status"));
    }
}

