package model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int amountAvailable;
    private Category category;
    private int amountPurchased;

    public Product(String name, String description, double price, int amountAvailable, Category category, int amountPurchased) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountAvailable = amountAvailable;
        this.category = category;
        this.amountPurchased = amountPurchased;
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

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(int amountPurchased) {
        this.amountPurchased = amountPurchased;
    }
}
