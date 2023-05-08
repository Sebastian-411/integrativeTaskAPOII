package exceptions;

public class InvalidDateFormat extends Exception{
    public InvalidDateFormat() {
        super("La fecha ingresada no es valida");
    }
}
