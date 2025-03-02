package ecommerce.app.vendor_service.order;


import ecommerce.app.vendor_service.order.factory.OrderFactory;
import ecommerce.app.vendor_service.order.factory.NewOrderFactory;
import ecommerce.app.vendor_service.order.factory.ProcessedOrderFactory;

public class OrderFactoryProvider {

    public static OrderFactory getOrderFactory(String status) {
        switch (status) {
            case "New":
                return new NewOrderFactory();
            case "Processed":
                return new ProcessedOrderFactory();
            default:
                throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }
}
