package model;
import com.google.gson.Gson;
import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    private ArrayList<Order> orders;
    private ArrayList<Product> products;
    private final String folder = "data";
    private final String order = "data/order.txt";
    private final String product = "data/product.txt";

    private ArrayList<Product> tmp = new ArrayList<Product>();
    private ArrayList<Order> tmp2 = new ArrayList<Order>();

    public Controller() {
        orders = new ArrayList<>();
        products = new ArrayList<>();
    }

    public String searchOrderWithoutRange(int input, String goal) throws SearchNotFoundException, InvalidPriceException, InvalidAmountException {
        switch (input){
            case 1:
                tmp2 = searchOrderByClientName(goal);
                break;
            case 2:
                tmp2 = searchOrderByTotalPrice(Integer.parseInt(goal));
                break;
            case 3:
                // tmp2 = searchOrderByDate(Double.parseDouble(goal)); Ajutar esto
                break;

            default:
                return "Por favor ingresa una opcion valida";
        }
        String txt = "";
        for (int i = 0; i < tmp2.size(); i++){
            txt = txt + i + ". " + tmp2.get(i).getBuyersName() + ": " + tmp2.get(i).getTotalPrice() + tmp2.get(i).getPurchaseDate().toString() + "\n";
        }
        return txt;
    }

    public String searchOrderWithRange(int input, String lowerLimit, String upperLimit) throws SearchNotFoundException, InvalidPriceException, InvalidAmountException {
        switch (input) {
            case 1:
                tmp2 = searchRangeOrderByBuyerName(lowerLimit, upperLimit);
                break;
            case 2:
                tmp2 = searchRangeOrderByTotalPrice(Double.parseDouble(lowerLimit), Double.parseDouble(upperLimit));
                break;
            case 3:
                // tmp2 = searchRangeOrderByDate(Double.parseDouble(lowerLimit), Double.parseDouble(upperLimit)); Ajutar esto
                break;
            default:
                return "Por favor ingresa una opcion valida";
        }
        String txt = "";
        for (int i = 0; i < tmp2.size(); i++){
            txt = txt + i + ". " + tmp2.get(i).getBuyersName() + ": " + tmp2.get(i).getTotalPrice() + tmp2.get(i).getPurchaseDate().toString() + "\n";
        }
        return txt;
    }

    public String searchProductWithoutRange(int input, String goal) throws SearchNotFoundException, InvalidPriceException, InvalidAmountException {
        switch (input){
            case 1:
                tmp = searchProductByName(goal);
                break;
            case 2:
                tmp = searchProductByCategory(Integer.parseInt(goal));
                break;
            case 3:
                tmp = searchProductByPrice(Double.parseDouble(goal));
                break;
            case 4:
                tmp = searchProductByAvailableAmount(Integer.parseInt(goal));
                break;
            case 5:
                tmp = searchProductByPurchasedTime(Integer.parseInt(goal));
                break;
            default:
                return "Por favor ingresa una opcion valida";
        }
        String txt = "";
        for (int i = 0; i < tmp.size(); i++){
            txt = txt + i + ". " + tmp.get(i).getName() + ": " + tmp.get(i).getPrice() + "\n";
        }
        return txt;
    }


    public String searchProductWithRange(int input, String lowerLimit, String upperLimit) throws SearchNotFoundException, InvalidPriceException, InvalidAmountException {
        switch (input) {
            case 1:
                tmp = searchRangeProductByName(lowerLimit, upperLimit);
                break;
            case 2:
                tmp = searchRangeProductByPrice(Double.parseDouble(lowerLimit), Double.parseDouble(upperLimit));
                break;
            case 3:
                tmp = searchRangeProductByAvailableAmount(Integer.parseInt(lowerLimit), Integer.parseInt(upperLimit));
                break;
            default:
                return "Por favor ingresa una opcion valida";
        }
        String txt = "";
        for (int i = 0; i < tmp.size(); i++){
            txt = txt + i + ". " + tmp.get(i).getName() + ": " + tmp.get(i).getPrice() + "\n";
        }
        return txt;
    }

    public boolean registerProduct(String name, String description, double price, int availableAmount, int category, int purchasedTimes) throws InvalidPriceException, InvalidAmountException {
        Product p = new Product(name, description, price, availableAmount, Category.values()[category], purchasedTimes);
        return products.add(p);
    }

    public boolean registerOrder(String name, int producto, int cantidad) throws ListEmptyException, InsufficientAmountException {
        ArrayList<Product> products1 = new ArrayList<>();
        if (tmp.get(producto).getAvailableAmount() - cantidad < 0){
            throw new InsufficientAmountException();
        }
        for(int j = 0; j<cantidad; j++){
            products1.add(tmp.get(producto));
        }
        Order o = new Order(name, products1);
        return orders.add(o);
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
            if(array != null){
                Collections.addAll(orders, array);
            }
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
            if(array != null){
                Collections.addAll(products, array);
            }
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
        products.sort((a, b) -> {return a.getName().compareTo(b.getName());
        });
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
        System.out.println(mid);
        return binSearchProductByName(searchIt, begin, mid, end);
    }
    public ArrayList<Product> searchProductByPrice(double goal) throws SearchNotFoundException, InvalidPriceException {
        if(goal<0){
            throw new InvalidPriceException();
        }
        products.sort((a, b) -> {
            int criteria1 = (int) (a.getPrice()-b.getPrice());
            if(criteria1 == 0){
                int x = (int)(a.getPrice() - ((int)a.getPrice()))*100;
                int y = (int)(b.getPrice() - ((int)b.getPrice()))*100;
                criteria1 = x-y;
            }
            return criteria1;
        });
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
        } else if(end-begin == 0 ){
            return null;
        } else if(products.get(mid).getAvailableAmount() > (searchIt)){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByAvailableAmount(searchIt, begin, mid, end);
    }
    public ArrayList<Product> searchProductByPurchasedTime(int goal) throws InvalidAmountException, SearchNotFoundException {
        if(goal<0){
            throw new InvalidAmountException();
        }
        products.sort((a, b) -> {
            int criteria1 = a.getPurchasedTimes()-(b.getPurchasedTimes());
            if(criteria1 == 0){
                criteria1 = a.getName().compareTo(b.getName());
            }
            return criteria1;
        });
        ArrayList<Product>  tmp = binSearchProductByPurchaseTime(goal, 0, ((products.size()-1)/2), products.size()-1);
        if(tmp == null){
            throw new SearchNotFoundException();
        }
        return tmp;
    }
    private ArrayList<Product> binSearchProductByPurchaseTime(int searchIt, int begin, int mid, int end){
        if(products.get(mid).getPurchasedTimes() == (searchIt)){
            ArrayList<Product> g = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(products.get(i).getPurchasedTimes()==(searchIt)){
                    g.add(products.get(i));
                }
            }
            return g;
        } else if(end-begin == 1 ){
            return null;
        } else if(products.get(mid).getPurchasedTimes() > (searchIt)){
            end = mid - 1;
        } else {
            begin = mid+1;
        }
        mid = (end+begin)/2;
        return binSearchProductByPurchaseTime(searchIt, begin, mid, end);
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
    // idk how to make search by date cuack cuack

    public void increaseStock(int i, int amount) throws InvalidAmountException {
        tmp.get(i).increaseStock(amount);
    }
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
        if(String.valueOf(products.get(mid).getName().charAt(0)).compareToIgnoreCase(lowerLimit) > 0 && String.valueOf(products.get(mid).getName().charAt(0)).compareToIgnoreCase(upperLimit) < 0){
            ArrayList<Product> listOfProductsFound = new ArrayList<>();
            for (int i = begin; i <= end; i++){
                if(String.valueOf(products.get(i).getName().charAt(0)).compareToIgnoreCase(lowerLimit) >= 0 && String.valueOf(products.get(i).getName().charAt(0)).compareToIgnoreCase(upperLimit) <= 0){
                    listOfProductsFound.add(products.get(i));
                }
            }
            return listOfProductsFound;
        } else if(end-begin == 0){
            return null;
        } else if(String.valueOf(products.get(mid).getName().charAt(0)).compareToIgnoreCase(upperLimit) >0){
            end = mid - 1;
        } else if(String.valueOf(products.get(mid).getName().charAt(0)).compareToIgnoreCase(lowerLimit) <0){
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


    //////////////// Order

    private void ordersSortByPrice() {
        orders.sort((a, b) -> {
            int criteria1 = (int) (a.getTotalPrice()-b.getTotalPrice());
            if(criteria1 == 0){
                int x = (int)(a.getTotalPrice() - ((int)a.getTotalPrice()))*100;
                int y = (int)(b.getTotalPrice() - ((int)b.getTotalPrice()))*100;
                criteria1 = x-y;
            }
            return criteria1;
        });
    }
    private void ordersSortByBuyersName(){
        orders.sort((a, b) -> {return a.getBuyersName().compareTo(b.getBuyersName());});
    }

    public ArrayList<Order> searchRangeOrderByTotalPrice(double upperLimit, double lowerLimit) throws SearchNotFoundException, InvalidPriceException {
        if(upperLimit<0 || lowerLimit<0){
            throw new InvalidPriceException();
        }
        ordersSortByPrice();
        //orders.forEach(o -> System.out.println(o.getTotalPrice()));



        ArrayList<Order> listOfOrdersFound = binSearchRangeOrderByTotalPrice(upperLimit, lowerLimit);



        if(listOfOrdersFound == null || listOfOrdersFound.size() == 0){
            throw new SearchNotFoundException();
        }
        return listOfOrdersFound;

    }

    private ArrayList<Order> binSearchRangeOrderByTotalPrice(double upperLimit, double lowerLimit){
        ArrayList<Order> listOfOrdersFound = new ArrayList<>();


        int begin = 0;
        int end = products.size() - 1;
        while (begin <= end){
            int mid = (end+begin)/2;
            if(orders.get(mid).getTotalPrice() <= (upperLimit) && orders.get(mid).getTotalPrice() >= (lowerLimit)){
                for (int i = mid; i >= begin; i--) {
                    if ((orders.get(i).getTotalPrice() <= (upperLimit) && orders.get(i).getTotalPrice() >= (lowerLimit))){
                        listOfOrdersFound.add(orders.get(i));
                    }
                }
                for (int i = mid+1; i <= end ; i++) {
                    listOfOrdersFound.add(orders.get(i));
                }
                return listOfOrdersFound;
            }else if(lowerLimit > (orders.get(mid).getTotalPrice())){
                begin = mid+1;
            }else if(upperLimit < (orders.get(mid).getTotalPrice())){
                end = mid-1;
            }
        }
        return  listOfOrdersFound;
    }


    public ArrayList<Order> searchRangeOrderByBuyerName(String upperLimit, String lowerLimit) throws SearchNotFoundException {
        ordersSortByBuyersName();
        //orders.forEach(p -> System.out.println(p.getBuyersName() + "\n"));

        ArrayList<Order> listOfOrdersFound = binSearchOrderByRangeBuyerName(upperLimit, lowerLimit);
        if(listOfOrdersFound == null){
            throw new SearchNotFoundException();
        }
        return listOfOrdersFound;

    }
    private ArrayList<Order> binSearchOrderByRangeBuyerName(String upperLimit, String lowerLimit){
        ArrayList<Order> listOfOrdersFound = new ArrayList<>();

        int begin = 0;
        int end = orders.size() - 1;
        while (begin <= end){
            int mid = (end+begin)/2;
            if(String.valueOf(orders.get(mid).getBuyersName().charAt(0)).compareToIgnoreCase(lowerLimit) >= 0 && String.valueOf(orders.get(mid).getBuyersName().charAt(0)).compareToIgnoreCase(upperLimit) <= 0){
                for (int i = mid; i >= begin; i--) {
                    if (String.valueOf(orders.get(i).getBuyersName().charAt(0)).compareToIgnoreCase(lowerLimit) >= 0 && String.valueOf(orders.get(i).getBuyersName().charAt(0)).compareToIgnoreCase(upperLimit) <= 0){
                        listOfOrdersFound.add(orders.get(i));
                    }
                }
                for (int i = mid+1; i <= end ; i++) {
                    if (String.valueOf(orders.get(i).getBuyersName().charAt(0)).compareToIgnoreCase(lowerLimit) >= 0 && String.valueOf(orders.get(i).getBuyersName().charAt(0)).compareToIgnoreCase(upperLimit) <= 0){
                        listOfOrdersFound.add(orders.get(i));
                    }
                }
                return listOfOrdersFound;
            }else if(String.valueOf(orders.get(mid).getBuyersName().charAt(0)).compareToIgnoreCase(lowerLimit) <0){
                begin = mid+1;
            }else if(String.valueOf(orders.get(mid).getBuyersName().charAt(0)).compareToIgnoreCase(upperLimit) >0){
                end = mid-1;
            }
        }
        return  listOfOrdersFound;
    }



}

