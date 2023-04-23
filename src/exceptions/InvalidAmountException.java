package exceptions;

public class InvalidAmountException extends Exception{
    public InvalidAmountException() {
        super("La cantidad ingresada no es valida");
    }
}
