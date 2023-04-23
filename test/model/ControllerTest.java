package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ControllerTest {
    private static Controller controller;

    public void setupStage4(){
        controller = new Controller();
        try{
            ArrayList<Product> p = new ArrayList<>();
            p.add(new Product("Guante", "Buenardo, buen relieve", 12.50, 10, Category.CLOTHING_AND_ACCESORIES, 5));
            p.add(new Product("Barnie", "Pequeño y suave", 15, 0, Category.TOYS_AND_GAMES, 5));
            p.add(new Product("Camisa", "Grande", 10, 11, Category.CLOTHING_AND_ACCESORIES, 55));
            p.add(new Product("Movie", "Aspera", 10, 0, Category.TOYS_AND_GAMES, 55));
            p.add(new Product("Tablet", "Electronic device", 250, 15, Category.ELECTRONIC, 3));
            controller.registerProduct(p);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void searchProductByName(){
    }

    @Test
    public void searchProductByName1(){
    }
    @Test
    public void searchProductByName2(){
    }
    @Test
    public void searchProductByName3(){
    }
    @Test
    public void searchProductByName4(){
    }
    @Test
    public void searchProductByName5(){
    }


    @Test
    public void searchRangeProductByName(){
    }
    @Test
    public void searchRangeProductByPrice(){
    }
    @Test
    public void searchRangeProductByAmountAvailable(){
    }
    @Test
    public void searchRangeProductByName1(){
    }
    @Test
    public void searchRangeProductByPrice1(){
    }
    @Test
    public void searchRangeProductByPrice2(){
    }
    @Test
    public void searchRangeProductByAmountAvailable1(){
    }


    @Test
    public void searchOrderBynameClient(){
    }
    @Test
    public void searchOrderByPriceTotal(){
    }
    @Test
    public void searchOrderByDate(){
    }
    @Test
    public void searchOrderBynameClient1(){
    }
    @Test
    public void searchOrderByPriceTotal1(){
    }
    @Test
    public void searchOrderByDate1(){
    }
    @Test
    public void searchOrderByPriceRange(){
    }
    @Test
    public void searchOrderByPriceRange1(){
    }
    @Test
    public void searchOrderByPriceRange2(){
    }
    @Test
    public void searchOrderByPriceRange3(){
    }
    @Test
    public void searchOrderByBuyerNameRange(){
    }
    @Test
    public void searchOrderByBuyerNameRange1(){
    }
    @Test
    public void searchOrderByBuyerNameRange2(){
    }
    @Test
    public void searchOrderByBuyerNameRange3(){
    }


}
