package exceptions;

public class InsufficientAmountException extends Exception{
    public InsufficientAmountException() {
        super("No existe el stock suficiente para tu compra");
    }
}
