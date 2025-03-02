package ecommerce.app.vendor_service.shipment;

import ecommerce.app.vendor_service.vendor.Vendor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends MongoRepository<Shipment, ObjectId> {
    Optional<Shipment> findByOrderId(ObjectId orderId);
    List<Shipment> findByVendor(ObjectId vendor);
}
