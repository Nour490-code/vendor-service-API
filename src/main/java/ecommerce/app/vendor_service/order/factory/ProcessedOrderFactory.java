package ecommerce.app.vendor_service.order.factory;


import ecommerce.app.vendor_service.order.Order;
import java.util.Date;

public class ProcessedOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(String address, String phoneNumber, Double totalAmount, String status) {
        Order order = new Order();
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setTotalAmount(totalAmount);
        order.setStatus("Processed");
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        return order;
    }
}
