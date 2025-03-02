package ecommerce.app.vendor_service.product;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection = "products")
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;
    private Instant createdAt;
    private Instant updatedAt;
    private ObjectId vendorID;

    public Product(String name, double price, String description, String category, int stockQuantity, ObjectId vendorID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.vendorID = vendorID;
    }

    public ObjectId getVendorID() {
        return vendorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
