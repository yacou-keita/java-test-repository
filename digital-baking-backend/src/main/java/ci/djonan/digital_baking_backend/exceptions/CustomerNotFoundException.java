package ci.djonan.digital_baking_backend.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message){
        super(message);
    }
}
