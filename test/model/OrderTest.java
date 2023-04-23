package model;


import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class OrderTest {
    private static ArrayList<Product> p;

    public void setupStage4(){
        try {
            p = new ArrayList<>();
            p.add(new Product("Guante", "Buenardo, buen relieve", 12.50, 10, Category.CLOTHING_AND_ACCESORIES, 5));
            p.add(new Product("Barnie", "Pequeño y suave", 15, 0, Category.TOYS_AND_GAMES, 5));
            p.add(new Product("Camisa", "Grande", 10, 11, Category.CLOTHING_AND_ACCESORIES, 55));
            p.add(new Product("Movie", "Aspera", 10, 0, Category.TOYS_AND_GAMES, 55));
            p.add(new Product("Tablet", "Electronic device", 250, 15, Category.ELECTRONIC, 3));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void registerOrder(){
        setupStage4();
        Order order = null;
        try{
            order = new Order("Ricardo", new ArrayList<Product>());
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(order.getListOfProducts(), p);
    }

    @Test
    public void registerOrder1(){
        setupStage4();
        Order order = null;
        ArrayList<Product> x = new ArrayList<>();
        x.add(p.get(0));
        x.add(p.get(2));
        try{
            order = new Order("Ricardo", x);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(order.getListOfProducts(), x);
        assertEquals(order.getTotalPrice(),22.50);
    }

    @Test
    public void registerOrder2(){
        setupStage4();
        Order order = null;
        ArrayList<Product> x = new ArrayList<>();
        x.add(p.get(0));
        x.add(p.get(2));
        try{
            order = new Order("Ricardo", x);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(order.getListOfProducts().get(0).getAvailableAmount(), 9);
        assertEquals(order.getListOfProducts().get(0).getPurchasedTimes(), 6);
        assertEquals(order.getListOfProducts().get(1).getAvailableAmount(), 10);
        assertEquals(order.getListOfProducts().get(1).getPurchasedTimes(), 56);
    }

    @Test
    public void registerOrder3(){
        setupStage4();
        Order order = null;
        ArrayList<Product> x = new ArrayList<>();
        x.add(p.get(0));
        x.add(p.get(1));
        try{
            order = new Order("Ricardo", x);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(order.getListOfProducts(), x); // esto deberia dar excepcion
    }


}
