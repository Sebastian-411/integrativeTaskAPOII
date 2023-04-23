package model;
import exceptions.SearchNotFoundException;
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
    public void searchProductByName() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByName("Guante");
        assertEquals("Guante", test.get(0).getName());

    }

    @Test
    public void searchProductByPrice() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByPrice(15);
        assertEquals("Barnie", test.get(0).getName());
    }
    @Test
    public void searchProductByCategory() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByCategory(7);
        assertEquals("Barnie, Movie", test.get(0).getName()+", "+test.get(1).getName());
    }
    @Test
    public void searchProductByAmountAvailable() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByAvailableAmount(0);
        assertEquals("Barnie", test.get(0).getName());
    }
    @Test
    public void searchProductByName2(){
        setupStage4();
        assertThrows(SearchNotFoundException.class, () -> {
            ArrayList<Product> test = controller.searchProductByName("Ropita");
        });
    }
    @Test
    public void searchProductByPrice1() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByPrice(10.0);
        assertEquals("Camisa, Movie", test.get(0).getName()+", "+test.get(1).getName());

    }


    @Test
    public void searchRangeProductByName(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByPrice(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByAmountAvailable(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByName1(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByPrice1(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByPrice2(){
        assertTrue(false);

    }
    @Test
    public void searchRangeProductByAmountAvailable1(){
        assertTrue(false);

    }


    @Test
    public void searchOrderBynameClient(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceTotal(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByDate(){
        assertTrue(false);

    }
    @Test
    public void searchOrderBynameClient1(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceTotal1(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByDate1(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceRange(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceRange1(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceRange2(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByPriceRange3(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByBuyerNameRange(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByBuyerNameRange1(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByBuyerNameRange2(){
        assertTrue(false);

    }
    @Test
    public void searchOrderByBuyerNameRange3(){
        assertTrue(false);

    }


}
