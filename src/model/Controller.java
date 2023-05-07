package model;
import com.google.gson.Gson;
import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private String folder = "data";
    private String order = "data/order.txt";
    private String product = "data/product.txt";

    public Controller() {
    }

    public boolean registerProduct(String name, String description, double price, int availableAmount, int category, int purchasedTimes) throws InvalidPriceException, InvalidAmountException {
        Product p = new Product(name, description, price, availableAmount, Category.values()[category], purchasedTimes);
        return products.add(p);
    }

    public String showCategory(){
        String txt = "";
        for (int i = 0; i < Category.values().length; i++){
            txt = txt  + (i+1) + ". " + Category.values()[i];
        }
        return txt;
    }

    public void loadOrder() throws IOException {
        File file = new File(order);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            Gson gson = new Gson();
            Order[] array = gson.fromJson(content, Order[].class);
            Collections.addAll(orders, array);
            fis.close();
        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void loadProduct() throws IOException {
        File file = new File(product);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            Gson gson = new Gson();
            Product[] array = gson.fromJson(content, Product[].class);
            Collections.addAll(products, array);
            fis.close();
        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void saveOrder() throws IOException {
        File file = new File(order);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(orders);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();

    }
    public void saveProduct() throws IOException {
        File file1 = new File(product);
        FileOutputStream fos1 = new FileOutputStream(file1);
        Gson gson1 = new Gson();
        String data1 = gson1.toJson(products);
        BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos1));
        writer1.write(data1);
        writer1.flush();
        fos1.close();
    }

    public void registerProduct(ArrayList<Product> x){
        products.addAll(x);
    }

    public void registerOrder(ArrayList<Order> y){
        orders.addAll(y);
    }

    public ArrayList<Product> searchProductByName(String goal) throws SearchNotFoundException {
        productsSortByName();
        ArrayList<Product> x = binSearchProductByName(goal, 0, ((products.size()-1)/2), products.size()-1);
        if(x == null){
            throw new SearchNotFoundException();
        }
        return x;

    }
    private ArrayList<Product> binSearchProductByName(String searchIt, int begin, int mid, int end){
        if(products.get(mid).getName().equalsIgnoreCase(searchIt)){
            ArrayList<Product> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getName().equalsIgnoreCase(searchIt)){
                    g.add(products.get(i));
                }
            }
            return g;
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getName().compareToIgnoreCase(searchIt) >0){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByName(searchIt, begin, mid, end);
    }
    public ArrayList<Product> searchProductByPrice(double goal) throws SearchNotFoundException, InvalidPriceException {
        if(goal<0){
            throw new InvalidPriceException();
        }
        productsSortByPrice();
        ArrayList<Product> x = binSearchProductByPrice(goal, 0, ((products.size()-1)/2), products.size()-1);
        if(x == null){
            throw new SearchNotFoundException();
        }
        return x;

    }
    private ArrayList<Product> binSearchProductByPrice(double searchIt, int begin, int mid, int end){
        if(products.get(mid).getPrice() == (searchIt)){
            ArrayList<Product> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getPrice()==(searchIt)){
                    g.add(products.get(i));
                }
            }
            return g;
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getPrice() > (searchIt)){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByPrice(searchIt, begin, mid, end);
    }
    public ArrayList<Product> searchProductByCategory(int i) throws SearchNotFoundException {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        Category category = Category.values()[i];
        Collections.sort(products, (a,b) -> {
            int criteria1 = a.getCategory().compareTo(a.getCategory());
            if(criteria1==0){
                criteria1 = a.getName().compareTo(a.getName());
            }
            return criteria1;
        });
        for (Product p: products){
            if(p.getCategory().equals(category)){
                filteredProducts.add(p);
            }
        }
        ArrayList<Product> x = filteredProducts;
        if(x.isEmpty()){
            throw new SearchNotFoundException();
        }
        return x;
    }
    public ArrayList<Product> searchProductByAvailableAmount(int goal) throws SearchNotFoundException, InvalidAmountException {
        if(goal<0){
            throw new InvalidAmountException();
        }
        products.sort((a, b) -> {
            int criteria1 = a.getAvailableAmount() - b.getAvailableAmount();
            if(criteria1 == 0){
                criteria1 = a.getName().compareTo(b.getName());
            }
            return criteria1;
        });
        ArrayList<Product> x = binSearchProductByAvailableAmount(goal, 0, ((products.size()-1)/2), products.size()-1);
        if(x == null){
            throw new SearchNotFoundException();
        }
        return x;

    }
    private ArrayList<Product> binSearchProductByAvailableAmount(int searchIt, int begin, int mid, int end){
        if(products.get(mid).getAvailableAmount() == (searchIt)){
            ArrayList<Product> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getAvailableAmount()==(searchIt)){
                    g.add(products.get(i));
                }
            }
            return g;
        } else if(end-begin == 1 ){
            return null;
        } else if(products.get(mid).getAvailableAmount() > (searchIt)){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByAvailableAmount(searchIt, begin, mid, end);
    }
    public ArrayList<Order> searchOrderByClientName(String goal) throws SearchNotFoundException {
        orders.sort((a, b) -> {return a.getBuyersName().compareTo(b.getBuyersName());
        });
        ArrayList<Order> x = binsearchOrderByClientName(goal, 0, ((orders.size()-1)/2), orders.size()-1);
        if(x == null){
            throw new SearchNotFoundException();
        }
        return x;

    }
    private ArrayList<Order> binsearchOrderByClientName(String searchIt, int begin, int mid, int end){
        if(orders.get(mid).getBuyersName().equalsIgnoreCase(searchIt)){
            ArrayList<Order> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(orders.get(i).getBuyersName().equalsIgnoreCase(searchIt)){
                    g.add(orders.get(i));
                }
            }
            return g;
        } else if(end-begin == 0 ){
            return null;
        } else if(orders.get(mid).getBuyersName().compareToIgnoreCase(searchIt) >0){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binsearchOrderByClientName(searchIt, begin, mid, end);
    }
    public ArrayList<Order> searchOrderByTotalPrice(double goal) throws SearchNotFoundException, InvalidPriceException {
        if(goal<0){
            throw new InvalidPriceException();
        }
        orders.sort((a, b) -> {
            int criteria1 = (int) (a.getTotalPrice()-b.getTotalPrice());
            if(criteria1 == 0){
                int x = (int)(a.getTotalPrice() - ((int)a.getTotalPrice()))*100;
                int y = (int)(b.getTotalPrice() - ((int)b.getTotalPrice()))*100;
                criteria1 = x-y;
            }
            return criteria1;
        });
        ArrayList<Order> x = binsearchOrderByTotalPrice(goal, 0, ((orders.size()-1)/2), orders.size()-1);
        if(x == null){
            throw new SearchNotFoundException();
        }
        return x;

    }
    private ArrayList<Order> binsearchOrderByTotalPrice(double searchIt, int begin, int mid, int end){
        if(orders.get(mid).getTotalPrice() == (searchIt)){
            ArrayList<Order> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(orders.get(i).getTotalPrice()==(searchIt)){
                    g.add(orders.get(i));
                }
            }
            return g;
        } else if(end-begin == 0 ){
            return null;
        } else if(orders.get(mid).getTotalPrice() > (searchIt)){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binsearchOrderByTotalPrice(searchIt, begin, mid, end);
    }
    // IDK how to make search by date cuack cuack

    public ArrayList<Product> searchRangeProductByPrice(double upperLimit, double lowerLimit) throws SearchNotFoundException, InvalidPriceException {
        if(upperLimit<0 || lowerLimit<0){
            throw new InvalidPriceException();
        }
        productsSortByPrice();
        ArrayList<Product> listOfProductsFound = binSearchProductByPriceRange(upperLimit, lowerLimit, 0, ((products.size()-1)/2), products.size()-1);
        if(listOfProductsFound == null){
            throw new SearchNotFoundException();
        }
        return listOfProductsFound;

    }



    private ArrayList<Product> binSearchProductByPriceRange(double upperLimit, double lowerLimit, int begin, int mid, int end){
        if(products.get(mid).getPrice() <= (upperLimit) && products.get(mid).getPrice() >= (lowerLimit)){
            ArrayList<Product> listOfProductsFound = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getPrice() <= (upperLimit) && products.get(i).getPrice() >= (lowerLimit)){
                    listOfProductsFound.add(products.get(i));
                }
            }
            return listOfProductsFound;
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getPrice() > (upperLimit)){
            end = mid - 1;
        } else if(products.get(mid).getPrice() < (lowerLimit)){
            begin = mid + 1;
        }
        mid = (end+begin)/2;
        return binSearchProductByPriceRange(upperLimit, lowerLimit, begin, mid, end);
    }

    private void productsSortByPrice() {
        products.sort((a, b) -> {
            int criteria1 = (int) (a.getPrice()-b.getPrice());
            if(criteria1 == 0){
                int x = (int)(a.getPrice() - ((int)a.getPrice()))*100;
                int y = (int)(b.getPrice() - ((int)b.getPrice()))*100;
                criteria1 = x-y;
            }
            return criteria1;
        });
    }
    private void productsSortByName(){
        products.sort((a, b) -> {return a.getName().compareTo(b.getName());});
    }



    public ArrayList<Product> searchRangeProductByName(String upperLimit, String lowerLimit) throws SearchNotFoundException {
        productsSortByName();

        ArrayList<Product> listOfProductsFound = binSearchProductByRangeName(upperLimit, lowerLimit, 0, ((products.size()-1)/2), products.size()-1);
        if(listOfProductsFound == null){
            throw new SearchNotFoundException();
        }
        return listOfProductsFound;

    }
    private ArrayList<Product> binSearchProductByRangeName(String upperLimit, String lowerLimit, int begin, int mid, int end){
        if(products.get(mid).getName().compareToIgnoreCase(lowerLimit) >= 0 && products.get(mid).getName().compareToIgnoreCase(upperLimit) <= 0){
            ArrayList<Product> listOfProductsFound = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getName().compareToIgnoreCase(lowerLimit) >= 0 && products.get(i).getName().compareToIgnoreCase(upperLimit) <= 0){
                    listOfProductsFound.add(products.get(i));
                }
            }
            return listOfProductsFound;
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getName().compareToIgnoreCase(upperLimit) >0){
            end = mid - 1;
        } else if(products.get(mid).getName().compareToIgnoreCase(lowerLimit) <0){
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByRangeName(upperLimit, lowerLimit, begin, mid, end);
    }


    public ArrayList<Product> searchRangeProductByAvailableAmount(int upperLimit, int lowerLimit) throws SearchNotFoundException, InvalidAmountException {
        if(upperLimit < 0 || lowerLimit < 0){
            throw new InvalidAmountException();
        }
        products.sort((a, b) -> {
            int criteria1 = a.getAvailableAmount() - b.getAvailableAmount();
            if(criteria1 == 0){
                criteria1 = a.getName().compareTo(b.getName());
            }
            return criteria1;
        });



        ArrayList<Product> listOfProductsFound = new ArrayList<>();


        int begin = 0;
        int end = products.size() - 1;
        while (begin <= end){
            int mid = (end+begin)/2;
            if(products.get(mid).getAvailableAmount() <= (upperLimit) && products.get(mid).getAvailableAmount() >= (lowerLimit)){
                for (int i = mid; i >= begin; i--) {
                    if ((products.get(i).getAvailableAmount() <= (upperLimit) && products.get(i).getAvailableAmount() >= (lowerLimit))){
                        listOfProductsFound.add(products.get(i));
                    }
                }
                for (int i = mid+1; i <= end ; i++) {
                    listOfProductsFound.add(products.get(i));
                }
                return listOfProductsFound;
            }else if(lowerLimit > (products.get(mid).getAvailableAmount())){
                begin = mid+1;
            }else if(upperLimit < (products.get(mid).getAvailableAmount())){
                end = mid-1;
            }
        }

        if(listOfProductsFound == null){
            throw new SearchNotFoundException();
        }
        return listOfProductsFound;

    }
    private ArrayList<Product> binSearchProductByRangeAvailableAmount(int upperLimit, int lowerLimit, int begin, int mid, int end){
        if(products.get(mid).getAvailableAmount() <= (upperLimit) && products.get(mid).getAvailableAmount() >= (lowerLimit)){
            ArrayList<Product> listOfProductsFound = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(mid).getAvailableAmount() <= (upperLimit) && products.get(mid).getAvailableAmount() >= (lowerLimit)){
                    listOfProductsFound.add(products.get(i));
                }
            }
            return listOfProductsFound;
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getAvailableAmount() > (upperLimit)){
            end = mid - 1;
        } else if(products.get(mid).getAvailableAmount() < (lowerLimit)){
            begin = mid + 1;
        }
        mid = (end+begin)/2;
        return binSearchProductByRangeAvailableAmount(upperLimit, lowerLimit, begin, mid, end);
    }

}

