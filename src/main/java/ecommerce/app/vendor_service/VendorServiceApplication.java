package ecommerce.app.vendor_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO:
//	-P1:
//		- Shipment:
//			- updateShipment(id : String, carrier : String): Shipment
//			- updateShipment(id : String, shipmentDate : LocalDateTime): Shipment
//	-P2:
//	-P3:
//		- What is the Instant type used in Product Model?
//		- Add Middleware to authenticate for when trying to create a product


@SpringBootApplication
public class VendorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorServiceApplication.class, args);
	}

}
