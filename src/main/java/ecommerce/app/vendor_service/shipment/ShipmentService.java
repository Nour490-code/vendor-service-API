package ecommerce.app.vendor_service.shipment;

import ecommerce.app.vendor_service.exceptions.EntityAlreadyExists;
import ecommerce.app.vendor_service.exceptions.EntityNotFoundException;
import ecommerce.app.vendor_service.vendor.Vendor;
import ecommerce.app.vendor_service.vendor.VendorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private VendorService vendorService;

    public List<Shipment> getShipments(ObjectId vendor){
        Vendor existingVendor = vendorService.getVendorById(vendor);
        return shipmentRepository.findByVendor(vendor);
    }

    public Shipment createShipment(String carrier, ObjectId orderId, ObjectId vendorId) {
        Vendor existingVendor = vendorService.getVendorById(vendorId);

        Optional<Shipment> existingShipment = shipmentRepository.findByOrderId(orderId);
        if (existingShipment.isPresent())
            throw new EntityAlreadyExists("A Shipment with the given Order ID has already been requested.");

        Shipment shipment = new Shipment(carrier, orderId, vendorId);
        vendorService.addShipmentToVendor(existingVendor, shipment);

        return shipmentRepository.insert(shipment);
    }
    public Shipment updateShipment(ObjectId id, String carrier){
        Shipment existingShipment = shipmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Shipment"));
        existingShipment.setCarrier(carrier);
        return shipmentRepository.save(existingShipment);
    }
    public Shipment updateShipment(ObjectId id, LocalDate deliveryDate){
        Shipment existingShipment = shipmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Shipment"));
        existingShipment.setShipmentDeliveryDate(deliveryDate);
        return shipmentRepository.save(existingShipment);
    }
    public Shipment updateShipment(ObjectId id, LocalDate deliveryDate, String carrier){
        Shipment existingShipment = shipmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Shipment"));
        existingShipment.setShipmentDeliveryDate(deliveryDate);
        existingShipment.setCarrier(carrier);
        return shipmentRepository.save(existingShipment);
    }
}
