package ecommerce.app.vendor_service.vendor;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, ObjectId> {
    Optional<Vendor> findByEmail(String email);
}
