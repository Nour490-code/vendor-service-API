package ecommerce.app.vendor_service.shipment;

import ecommerce.app.vendor_service.vendor.Vendor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;

@Document(collection = "shipments")
@Data
@NoArgsConstructor
public class Shipment {
    @Id
    private ObjectId id;
    private String carrier;
    private int trackingNumber;
    private String status;
    private LocalDate shipmentCreationDate;
    private LocalDate shipmentDeliveryDate;
    private ObjectId orderId;
    private ObjectId vendor;

    public Shipment(String carrier, ObjectId orderId, ObjectId vendor) {
        this.carrier = carrier;
        this.trackingNumber = new Random().nextInt(10);
        this.status = "Open";
        this.shipmentCreationDate = LocalDate.now();
        this.shipmentDeliveryDate = this.shipmentCreationDate.plusDays(5);
        this.orderId = orderId;
        this.vendor = vendor;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getShipmentCreationDate() {
        return shipmentCreationDate;
    }

    public LocalDate getShipmentDeliveryDate() {
        return shipmentDeliveryDate;
    }

    public ObjectId getOrderId() {
        return orderId;
    }

    public void setShipmentDeliveryDate(LocalDate shipmentDeliveryDate) {
        this.shipmentDeliveryDate = shipmentDeliveryDate;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}


