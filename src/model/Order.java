package model;
import exceptions.InsufficientAmountException;
import exceptions.ListEmptyException;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String buyersName;
    private ArrayList<Product> listOfProducts;
    private double totalPrice;
    private Date purchaseDate;
    public Order(String buyersName, ArrayList<Product> p) throws ListEmptyException, InsufficientAmountException {
        if(verifyStock(p)){
            throw new InsufficientAmountException();
        }
        if(p.isEmpty()){
            throw new ListEmptyException();
        }
        this.buyersName = buyersName;
        this.totalPrice = calculateTotalAndBuy(p);
        this.purchaseDate = new Date();
        this.listOfProducts = p;
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

    public boolean verifyStock(ArrayList<Product> x){
        for (Product p: x){
            if(p.getAvailableAmount()-1<0){
                return true;
            }
        }
        return false;
    }


    private double calculateTotalAndBuy(ArrayList<Product> x){
        double y = 0;
        for (Product p: x){
            y += p.getPrice();
            p.buy();
        }
        return y;
    }
    
}
