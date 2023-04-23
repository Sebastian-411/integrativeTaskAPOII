package exceptions;

public class ListEmptyException extends Exception{
    public ListEmptyException() {
        super("La lista de productos ingresada esta vacia");
    }
}
