package exceptions;

public class InvalidRangeException extends Exception{
    public InvalidRangeException() {
        super("El rango ingresado no es valido");
    }
}
