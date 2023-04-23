package model;

import exceptions.InvalidAmountException;
import exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ProductTest {
    public static ArrayList<Product> p;
    public void setupStage3(){
        p = new ArrayList<>();
    }

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
    public void registerProduct(){
        setupStage3();
        assertThrows(InvalidPriceException.class, () -> {
            p.add(new Product("Barnie", "Pequeño y suave", -10, 0, Category.TOYS_AND_GAMES, 5));
        });
    }

    @Test
    public void registerProduct1(){
        setupStage3();
        assertThrows(InvalidAmountException.class, () -> {
            p.add(new Product("Barnie", "Pequeño y suave", 10, -15, Category.TOYS_AND_GAMES, 5));
        });
    }

    @Test
    public void registerProduct2(){
        setupStage3();
        assertThrows(InvalidAmountException.class, () -> {
            p.add(new Product("Barnie", "Pequeño y suave", 10, 15, Category.TOYS_AND_GAMES, -1));
        });
    }

    @Test
    public void registerProduct3(){
        setupStage4();
        try{
            p.add(new Product("Barnie", "Pequeño y suave", 10, 15, Category.TOYS_AND_GAMES, 1));
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(p.size(), 6);
    }

    @Test
    public void increaseStock(){
        setupStage4();
        try {
            p.get(4).increaseStock(5);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(p.get(4).getAvailableAmount(), 20);
    }

    @Test
    public void increaseStock1(){
        setupStage4();
        assertThrows(InvalidAmountException.class, () -> {
            p.get(4).increaseStock(-5);
        });
    }

    @Test
    public void increaseStock2(){
        setupStage4();
        try {
            p.get(4).increaseStock(0);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(p.get(4).getAvailableAmount(), 15);
    }
}
