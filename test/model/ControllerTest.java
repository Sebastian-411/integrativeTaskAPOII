package model;
import exceptions.InvalidAmountException;
import exceptions.InvalidPriceException;
import exceptions.InvalidRangeException;
import exceptions.SearchNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

    public void setupStage5(){
        controller = new Controller();
        try{
            ArrayList<Order> l = new ArrayList<>();
            ArrayList<Product> p = new ArrayList<>();
            p.add(new Product("Guante", "Buenardo, buen relieve", 12.50, 10, Category.CLOTHING_AND_ACCESORIES, 5));
            p.add(new Product("Barnie", "Pequeño y suave", 15, 0, Category.TOYS_AND_GAMES, 5));
            p.add(new Product("Camisa", "Grande", 10, 11, Category.CLOTHING_AND_ACCESORIES, 55));
            p.add(new Product("Movie", "Aspera", 10, 0, Category.TOYS_AND_GAMES, 55));
            p.add(new Product("Tablet", "Electronic device", 250, 15, Category.ELECTRONIC, 3));
            controller.registerProduct(p);
            ArrayList<Product> s = new ArrayList<>();
            s.add(p.get(0));
            l.add(new Order("Diaz", s));

            ArrayList<Product> x = new ArrayList<>();
            x.add(p.get(2));
            l.add(new Order("Diaz", x));

            ArrayList<Product> f = new ArrayList<>();
            f.add(p.get(4));
            Order tmp = new Order("Nerby Hernandez", f);
            tmp.setPurchaseDate(new SimpleDateFormat("dd/MM/yyyy").parse("04/10/2022"));
            l.add(tmp);
            controller.registerOrder(l);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void searchProductByName() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByName("Tablet");
        assertEquals("Tablet", test.get(0).getName());

    }

    @Test
    public void searchProductByPrice() throws SearchNotFoundException, InvalidPriceException {
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
    public void searchProductByAmountAvailable() throws SearchNotFoundException, InvalidAmountException {
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
    public void searchProductByPrice1() throws SearchNotFoundException, InvalidPriceException {
        setupStage4();
        ArrayList<Product> test = controller.searchProductByPrice(10.0);
        assertEquals("Camisa, Movie", test.get(0).getName()+", "+test.get(1).getName());

    }


    @Test
    public void searchRangeProductByName() throws SearchNotFoundException {
        setupStage4();
        ArrayList<Product> test = controller.searchRangeProductByName("D","A");
        assertEquals("Barnie, Camisa", test.get(0).getName()+", "+test.get(1).getName() );


    }
    @Test
    public void searchRangeProductByPrice() throws InvalidPriceException, SearchNotFoundException, InvalidRangeException {
        setupStage4();
        ArrayList<Product> test = controller.searchRangeProductByPrice(11,9);
        assertEquals("Camisa, Movie", test.get(0).getName()+", "+test.get(1).getName() );

    }
    @Test
    public void searchRangeProductByAmountAvailable() throws SearchNotFoundException, InvalidAmountException {
        setupStage4();
        ArrayList<Product> test = controller.searchRangeProductByAvailableAmount(14,10);
        assertEquals("Guante, Camisa", test.get(0).getName()+", "+test.get(1).getName() );

    }
    @Test
    public void searchRangeProductByName1() throws SearchNotFoundException {
        setupStage4();
        assertThrows(SearchNotFoundException.class, () -> {
            ArrayList<Product> x = controller.searchRangeProductByName("Z","X");
        });

    }
    @Test
    public void searchRangeProductByPrice1() throws InvalidPriceException, SearchNotFoundException {
        setupStage4();
        assertThrows(InvalidRangeException.class , () -> {
            ArrayList<Product> test = controller.searchRangeProductByPrice(50, 50);
        });


    }
    @Test
    public void searchRangeProductByPrice2(){
        setupStage4();
        assertThrows(InvalidRangeException.class , () -> {
            ArrayList<Product> test = controller.searchRangeProductByPrice(20, 50);
        });
    }
    @Test
    public void searchRangeProductByAmountAvailable1(){
        setupStage4();
        assertThrows(InvalidAmountException.class, () -> {
            ArrayList<Product> x = controller.searchRangeProductByAvailableAmount(-2,-10);
        });

    }


    @Test
    public void searchOrderBynameClient() throws SearchNotFoundException {
            setupStage5();
            ArrayList<Order> x = controller.searchOrderByClientName("Diaz");
            assertEquals("Diaz, Diaz", x.get(0).getBuyersName() + ", " + x.get(1).getBuyersName());
    }
    @Test
    public void searchOrderByPriceTotal() throws SearchNotFoundException, InvalidPriceException {
        setupStage5();

        ArrayList<Order> x = controller.searchOrderByTotalPrice(250);
        assertEquals("Tablet", x.get(0).getListOfProducts().get(0).getName());

    }
    @Test
    public void searchOrderByDate() throws SearchNotFoundException, ParseException {
        setupStage5();
        ArrayList<Order> test = controller.searchOrderByDate("04/10/2022");
        assertEquals( new SimpleDateFormat("dd/MM/yyyy").parse("04/10/2022")  ,test.get(0).getPurchaseDate());
        assertEquals("Nerby Hernandez" , test.get(0).getBuyersName());
        assertEquals(1, test.size());

    }
    @Test
    public void searchOrderBynameClient1() throws SearchNotFoundException {
        setupStage5();
        assertThrows(SearchNotFoundException.class, () -> {
            ArrayList<Order> x = controller.searchOrderByClientName("Lucila T");

        });
    }
    @Test
    public void searchOrderByDate1() throws SearchNotFoundException, ParseException {
        setupStage5();
        assertThrows(SearchNotFoundException.class, () -> {
            ArrayList<Order> test = controller.searchOrderByDate("12/12/2024");

        });
    }
    @Test
    public void searchOrderByPriceTotal1(){
        setupStage5();
        assertThrows(SearchNotFoundException.class, () -> {
            ArrayList<Order> x = controller.searchOrderByTotalPrice(10223);

        });
    }

    @Test
    public void searchOrderByPriceRange() throws InvalidPriceException, SearchNotFoundException, InvalidRangeException {
        setupStage5();
        ArrayList<Order> test = controller.searchRangeOrderByTotalPrice(50,5);
        assertEquals("Diaz, Diaz", test.get(0).getBuyersName()+", "+test.get(1).getBuyersName() );
        assertEquals(10, test.get(0).getTotalPrice());
        assertEquals(12.5, test.get(1).getTotalPrice());

    }
    @Test
    public void searchOrderByPriceRange1(){
        setupStage5();
        assertThrows(InvalidRangeException.class , () -> {
            ArrayList<Order> test = controller.searchRangeOrderByTotalPrice(10, 20);
        });

    }
    @Test
    public void searchOrderByPriceRange2(){
        setupStage5();
        assertThrows(InvalidRangeException.class , () -> {
            ArrayList<Order> test = controller.searchRangeOrderByTotalPrice(50, 50);
        });

    }
    @Test
    public void searchOrderByPriceRange3() throws InvalidPriceException, SearchNotFoundException, InvalidRangeException {
        setupStage5();
        ArrayList<Order> test = controller.searchRangeOrderByTotalPrice(250,5);
        assertEquals("Diaz, Diaz, Nerby Hernandez", test.get(0).getBuyersName()+", "+test.get(1).getBuyersName()+", "+test.get(2).getBuyersName() );
        assertEquals(12.5, test.get(0).getTotalPrice());
        assertEquals(10, test.get(1).getTotalPrice());
        assertEquals(250, test.get(2).getTotalPrice());
    }
    @Test
    public void searchOrderByBuyerNameRange() throws SearchNotFoundException {
        setupStage5();
        ArrayList<Order> test = controller.searchRangeOrderByBuyerName("D", "A");
        assertEquals("Diaz, Diaz" , test.get(0).getBuyersName() + ", " + test.get(1).getBuyersName());
        assertEquals(2 , test.size());

    }
    @Test
    public void searchOrderByBuyerNameRange1() throws SearchNotFoundException {
        setupStage5();
        ArrayList<Order> test = controller.searchRangeOrderByBuyerName("O", "D");
        assertEquals("Diaz, Diaz, Nerby Hernandez" , test.get(0).getBuyersName() + ", " + test.get(1).getBuyersName() + ", " + test.get(2).getBuyersName());
        assertEquals(3 , test.size());

    }
    @Test
    public void searchOrderByBuyerNameRange2() throws SearchNotFoundException {
        setupStage5();
        ArrayList<Order> test = controller.searchRangeOrderByBuyerName("O", "M");
        assertEquals("Nerby Hernandez" , test.get(0).getBuyersName() );
        assertEquals(1, test.size());
    }
    @Test
    public void searchOrderByBuyerNameRange3(){
        setupStage5();
        assertThrows(SearchNotFoundException.class , () -> {
            ArrayList<Order> test = controller.searchRangeOrderByBuyerName("X", "Z");
        });

    }


}
