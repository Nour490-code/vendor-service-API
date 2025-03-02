package ecommerce.app.vendor_service.vendor;

import ecommerce.app.vendor_service.exceptions.EntityAlreadyExists;
import ecommerce.app.vendor_service.shipment.Shipment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @PostMapping("/signup")
    public ResponseEntity<Vendor> signUp(@RequestBody Map<String,String> payload) throws EntityAlreadyExists {
        return new ResponseEntity<Vendor>(vendorService.createVendor(
                payload.get("name"), payload.get("email"),
                payload.get("password"),payload.get("phoneNumber")
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> payload) throws EntityAlreadyExists {
        return new ResponseEntity <Optional<String>>(vendorService.getVendorByEmail(
                payload.get("email"),
                payload.get("password")
        ),HttpStatus.OK);
    }

    @GetMapping("/shipments/{id}")
    public ResponseEntity<List<Shipment>> getAllVendorShipments(@PathVariable ObjectId id){
        return new ResponseEntity<List<Shipment>>(vendorService.allVendorShipments(id),HttpStatus.OK);
    }
}
