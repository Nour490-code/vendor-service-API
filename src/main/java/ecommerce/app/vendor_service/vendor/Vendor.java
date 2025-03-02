package ecommerce.app.vendor_service.vendor;

import ecommerce.app.vendor_service.shipment.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "vendors")
@Data
@NoArgsConstructor
public class Vendor {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private List<Shipment> shipments = new ArrayList<Shipment>();

    public Vendor(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public ObjectId getId() {
        return id;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addShipment(Shipment shipment) {
        this.shipments.add(shipment);
    }
}
