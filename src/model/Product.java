package model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int availableAmount;
    private Category category;
    private int purchasedTimes;

    public Product(String name, String description, double price, int availableAmount, Category category, int purchasedTimes) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableAmount = availableAmount;
        this.category = category;
        this.purchasedTimes = purchasedTimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPurchasedTimes() {
        return purchasedTimes;
    }

    public void setPurchasedTimes(int purchasedTimes) {
        this.purchasedTimes = purchasedTimes;
    }

    
}
