package ecommerce.app.vendor_service.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity) {
        super(entity + " Not Found");
    }
}
