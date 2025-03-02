package ecommerce.app.vendor_service.shipment;

import ecommerce.app.vendor_service.product.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/{vendorId}")
    public ResponseEntity<Shipment> createShipment(@RequestBody Map<String,String> payload, @PathVariable String vendorId){

        return new ResponseEntity<Shipment>(shipmentService.createShipment(
                payload.get("carrier"), new ObjectId(payload.get("orderId")), new ObjectId(vendorId)
        ), HttpStatus.CREATED);
    }
    @GetMapping("/vendor/{vendor}")
    public ResponseEntity<List<Shipment>> getAllShipments(@PathVariable String vendor){
        return new ResponseEntity<List<Shipment>>(shipmentService.getShipments(new ObjectId(vendor)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@RequestBody Map<String,String> payload , @PathVariable ObjectId id){
        if (!payload.get("carrier").isEmpty()){
            return new ResponseEntity<Shipment>(shipmentService.updateShipment(id, payload.get("carrier")),HttpStatus.OK);
        } else if (!payload.get("deliveryDate").isEmpty()) {
            return new ResponseEntity<Shipment>(shipmentService.updateShipment(id, payload.get("deliveryDate")),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Shipment>(shipmentService.updateShipment(id, LocalDate.parse(payload.get("deliveryDate")) ,payload.get("carrier")),HttpStatus.OK);
        }
    }
}
