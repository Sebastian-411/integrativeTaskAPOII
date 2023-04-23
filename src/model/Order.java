package model;
import java.sql.Date;
import java.util.ArrayList;

public class Order {
    private String buyersName;
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private double totalPrice;
    private Date purchaseDate;
    public Order(String buyersName, ArrayList<Product> p) {
        this.buyersName = buyersName;
        //this.totalPrice = totalPrice; Esto se debe calcular
        //this.purchaseDate = purchaseDate; Esto se debe calcular
        this.listOfProducts = p; // se debe agregar la excepcio cuando este vacio
    }
    public String getBuyersName() {
        return buyersName;
    }
    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }
    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        
    }
    public boolean addProduct(Product productToAdd) {
        if (productToAdd != null) {
            return listOfProducts.add(productToAdd);
        }
        return false;
    }
    public boolean addProduct(ArrayList<Product> productToAdd) {
        if (productToAdd != null) {
            return listOfProducts.addAll(productToAdd);
        }
        return false;
    }
}
