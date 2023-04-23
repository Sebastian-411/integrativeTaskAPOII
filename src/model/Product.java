package model;

import exceptions.*;

public class Product {
    private String name;
    private String description;
    private double price;
    private int availableAmount;
    private Category category;
    private int purchasedTimes;

    public Product(String name, String description, double price, int availableAmount, Category category, int purchasedTimes) throws InvalidPriceException, InvalidAmountException {
        if(price<0){
            throw new InvalidPriceException();
        }
        if(availableAmount<0 || purchasedTimes<0){
            throw new InvalidAmountException();
        }

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

    public void increaseStock(int x){

    }

    public void buy(){
        this.availableAmount = this.availableAmount-1;
        this.purchasedTimes ++;
    }

    @Override
    public String toString() {
        return  "name= " + name + '\n' +
                "description= " + description + '\n' +
                "price= " + price + '\n' +
                "availableAmount= " + availableAmount + '\n' +
                "category= " + category.toString() + '\n' +
                "purchasedTimes= " + purchasedTimes;
    }
}