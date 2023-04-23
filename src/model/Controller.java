package model;
import com.google.gson.Gson;
import exceptions.InvalidAmountException;
import exceptions.InvalidPriceException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller
 */
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
    
}

