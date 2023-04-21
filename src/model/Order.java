package model;
import java.sql.Date;
import java.util.ArrayList;

public class Order {
    private String buyersName;
    private ArrayList<Product> listOfProducts;
    private double totalPrice;
    private Date purchaseDate;
    public Order(String buyersName, double totalPrice, Date purchaseDate) {
        this.buyersName = buyersName;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.listOfProducts = new ArrayList<Product>();
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
}
