package exceptions;

public class SearchNotFoundException extends Exception{
    public SearchNotFoundException() {
        super("El elemento a buscar no se encuentra");
    }
}
