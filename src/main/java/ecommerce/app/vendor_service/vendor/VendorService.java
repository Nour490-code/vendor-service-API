    package ecommerce.app.vendor_service.vendor;

    import ecommerce.app.vendor_service.exceptions.EntityAlreadyExists;
    import ecommerce.app.vendor_service.exceptions.EntityNotFoundException;
    import ecommerce.app.vendor_service.shipment.Shipment;
    import org.bson.types.ObjectId;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Objects;
    import java.util.Optional;

    @Service
    public class VendorService {
        @Autowired
        private VendorRepository vendorRepository;

        public Vendor createVendor
                (String name, String email, String password, String phoneNumber)
        {
                Optional<Vendor> existingVendor = vendorRepository.findByEmail(email);
                if (existingVendor.isPresent()) throw new EntityAlreadyExists("This email is already in use");

                Vendor newVendor = vendorRepository.insert(new Vendor(name, email, password, phoneNumber));
                return newVendor;
        }

        public Optional<String> getVendorByEmail
                (String email, String password)
        {
            Optional<Vendor> existingVendor = vendorRepository.findByEmail(email);
            if (existingVendor.isEmpty() || !Objects.equals(existingVendor.get().getPassword(), password)) throw new EntityAlreadyExists("Incorrect E-mail or password");

            return existingVendor.get().getId().toString().describeConstable();
        }
        public Vendor getVendorById(ObjectId id){
            return vendorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Vendor"));
        }
        public void addShipmentToVendor(Vendor vendor, Shipment shipment) {
            vendor.addShipment(shipment);
            vendorRepository.save(vendor);
        }
        public List<Shipment> allVendorShipments(ObjectId id){
            Vendor vendor = this.getVendorById(id);
            return vendor.getShipments();
        }
    }
