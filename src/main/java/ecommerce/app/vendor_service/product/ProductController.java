package ecommerce.app.vendor_service.product;

import ecommerce.app.vendor_service.exceptions.EntityNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
//    Autowired instantiates the class below with the following object.(Short-Cut)
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.allProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getSingleProduct(@PathVariable ObjectId id) throws EntityNotFoundException {
            Optional<Product> product = productService.singleProduct(id);
            return new ResponseEntity(product, HttpStatus.OK);
    }
    @PostMapping()
//    Argument passed in the following method Maps whatever in the request body to:
//    Key<String> => Value<String> then parsing on demand
    public ResponseEntity<Product> createProduct(@RequestBody Map<String,String> payload){
//                    String jsonInput = String.format("{\"name\":\"%s\", \"price\":\"%f\", \"description\":%s, \"category\":\"%s\", \"stockQuantity\":%d, \"vendorId\":\"%s\"}",
        return new ResponseEntity<Product>(productService.createProduct(
                payload.get("name"), Double.parseDouble(payload.get("price")),
                payload.get("description"),payload.get("category"),
                Integer.parseInt(payload.get("stockQuantity")),
                new ObjectId(payload.get("vendorID"))
        ),HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Map<String,String> payload) throws EntityNotFoundException{
            return new ResponseEntity<Product>(productService.updateProduct(new ObjectId(id),
                    payload.get("name"), payload.get("description"),
                    !payload.get("price").isEmpty() ? Double.parseDouble(payload.get("price")) : null,
                    payload.get("category"),
                    !payload.get("stockQuantity").isEmpty() ? Integer.parseInt(payload.get("stockQuantity")) : null,
                    new ObjectId(payload.get("vendorID"))
            ), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id , @RequestBody Map<String,String> payload){
            return new ResponseEntity(productService.deleteProduct(
                    new ObjectId(id)
                    ,new ObjectId(payload.get("vendorID"))
            ),HttpStatus.OK);
    }
}
