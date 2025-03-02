package ecommerce.app.vendor_service.product;

import ecommerce.app.vendor_service.VendorServiceApplication;
import ecommerce.app.vendor_service.exceptions.EntityNotFoundException;
import ecommerce.app.vendor_service.exceptions.PermissionDeniedException;
import ecommerce.app.vendor_service.vendor.Vendor;
import ecommerce.app.vendor_service.vendor.VendorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
//    Autowired instantiates the class below with the following object.(Short-Cut)
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  VendorService vendorService;

    public List<Product> allProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> singleProduct(ObjectId id){
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product")));
    }

    public Product createProduct
            (String name, double price, String description, String category, int stockQuantity,ObjectId vendorID)
    {
        Product newProduct = productRepository.insert(new Product
                (name,  price,  description,  category, stockQuantity, vendorID)
        );
        return newProduct;
    }
    public Product updateProduct
            (ObjectId id,String name,String description, Double price,  String category, Integer stockQuantity, ObjectId vendorId)
    {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product"));
        Vendor existingVendor = vendorService.getVendorById(vendorId);

        if (!existingVendor.getId().equals(existingProduct.getVendorID())) throw new PermissionDeniedException("Permission Denied");
        if(!name.isEmpty() ) existingProduct.setName(name);
        if(price != null ) existingProduct.setPrice(price);
        if(!description.isEmpty() ) existingProduct.setDescription(description);
        if(stockQuantity != null ) existingProduct.setStockQuantity(stockQuantity);
        if(!category.isEmpty()) existingProduct.setCategory(category);
        existingProduct.setUpdatedAt(Instant.now());

        return productRepository.save(existingProduct);
    }
    public Map deleteProduct(ObjectId id, ObjectId vendorID){
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product"));
        Vendor existingVendor = vendorService.getVendorById(vendorID);
        if (!existingVendor.getId().equals(existingProduct.getVendorID())) throw new PermissionDeniedException("Permission Denied");
        else productRepository.delete(existingProduct);
        return Map.of(
                "deleted_id",id
        );
    }
}
