package exceptions;

public class InvalidPriceException extends Exception{
    public InvalidPriceException() {
        super("El precio ingresado no es valido");
    }
}
