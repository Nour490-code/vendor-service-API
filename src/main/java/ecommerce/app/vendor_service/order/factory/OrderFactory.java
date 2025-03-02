package ecommerce.app.vendor_service.order.factory;
import ecommerce.app.vendor_service.order.Order;
public interface OrderFactory {
    Order createOrder(String address, String phoneNumber, Double totalAmount, String status);
}
