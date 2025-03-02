package ecommerce.app.vendor_service.product;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Extends DB's repo and type of ID used.
//Does the Job of talking to the Database and getting data back.
@Repository
public interface ProductRepository extends MongoRepository <Product, ObjectId>{
}
