package ui;

import model.Category;
import model.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private Scanner sc;
    private Controller controller;
    public Manager() {
        sc = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {
        Manager m = new Manager();
        m.init();
        try {
            while (true) {
                m.menu();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        try {
            controller.loadProduct();
            controller.loadOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() throws IOException {
        System.out.println("Bienvenido a Mercadolibre Beta\n" +
                "Que podemos hacer por ti?\n" +
                "\t1. Registrar un producto\n" +
                "\t2. Incrementar inventario de un producto\n" +
                "\t3. Hacer un pedido\n" +
                "\t4. Buscar productos\n" +
                "\t5. Buscar pedidos\n" +
                "\t6. Salir");
        switch (Integer.parseInt(sc.nextLine())){
            case 1:
                registerProduct();
                break;
            case 2:
                increaseStock();
                break;
            case 3:
                registerOrder();
                break;
            case 4:
                System.out.println("1. Busqueda por rango \n 2. Busqueda sin rango");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        searchProductWithRange();
                        break;
                    case 2:
                        searchProductWithoutRange();
                        break;
                    default:
                        System.out.println("Por favor ingresa una opcion valida");
                        menu();
                }
                break;
            case 5:
                System.out.println("1. Busqueda por rango \n 2. Busqueda sin rango");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        searchOrderWithRange();
                        break;
                    case 2:
                        searchOrderWithoutRange();
                        break;
                    default:
                        System.out.println("Por favor ingresa una opcion valida");
                        menu();
                }

                break;
            case 6:
                controller.saveProduct();
                controller.saveOrder();
                System.exit(0);
                break;
            default:
                System.out.println("Por favor ingresa una opcion valida");
        }
    }
    
    public void increaseStock(){
        try {
            System.out.println("1. Busqueda por rango \n 2. Busqueda sin rango");
            switch (Integer.valueOf(sc.nextLine())) {
                case 1:
                    searchProductWithRange();
                    break;
                case 2:
                    searchProductWithoutRange();
                    break;
                default:
                    System.out.println("Por favor ingresa una opcion valida");
                    menu();
            }
            System.out.println("Ingresa el id del producto que desea aumentar el inventario");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa la cantidad que desea aumentar");
            int amount = Integer.parseInt(sc.nextLine());
            controller.increaseStock(id, amount);
            controller.saveProduct();
            System.out.println("Se ha cambiado con exito!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void registerProduct(){
        try {
            System.out.println("Estas registrando un producto \n Ingresa el nombre del producto");
            String name = sc.nextLine();
            System.out.println("Ingresa una descripcion del producto");
            String description = sc.nextLine();
            System.out.println("Ingresa el precio del producto");
            double price = Double.parseDouble(sc.nextLine());
            System.out.println("Ingresa la cantidad disponible del producto");
            int availableAmount = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa el indice de la categoria a la cual pertenece el producto");
            System.out.println(controller.showCategory());
            int category = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa el numero de veces que este producto se ha vendido");
            int purchasedTimes = Integer.parseInt(sc.nextLine());
            if ( controller.registerProduct(name, description, price, availableAmount, category, purchasedTimes) ){
                System.out.println("El producto fue creado con exito");
                controller.saveProduct();
            } else {
                System.out.println("Ha ocurrido un error:(");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void registerOrder() throws IOException {
        try {
            System.out.println("Estas registrando un pedido \n Ingresa el nombre del cliente");
            String name = sc.nextLine();
            String x = "Si";
            int cantidad = 0;
            int producto = 0;
            while(!x.equalsIgnoreCase("NO")) {
            System.out.println("Busca un producto");
            System.out.println("1. Busqueda por rango \n 2. Busqueda sin rango");
            switch (Integer.valueOf(sc.nextLine())){
                case 1:
                    searchProductWithRange();
                    break;
                case 2:
                    searchProductWithoutRange();
                    break;
                default:
                    System.out.println("Por favor ingresa una opcion valida");
                    menu();
            }
                System.out.println("Ingresa el id del producto que desea comprar");
                producto = Integer.parseInt(sc.nextLine());
                System.out.println("Ingresa la cantidad que desea comprar");
                cantidad = Integer.parseInt(sc.nextLine());
                System.out.println("Desear agregar otro producto? Si/No");
                x = sc.nextLine();
            }
            if(controller.registerOrder(name, producto, cantidad)){
                System.out.println("El pedido fue creado con exito");
                controller.saveOrder();
                controller.saveProduct();
            } else {
                System.out.println("Ha ocurrido un error:(");
            }
        } catch (Exception e){
            e.printStackTrace();
            menu();
        }
    }

    public void searchProductWithoutRange() throws IOException {
        try {
            System.out.println("1. Buscar producto por nombre" + "\n" +
                    "2. Buscar producto por categoria" + "\n" +
                    "3. Buscar producto por precio" + "\n" +
                    "4. Buscar producto por cantidad disponible" + "\n" +
                    "5. Buscar producto por cantidad vendida");
            int x = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa el valor a buscar");
            String y = sc.nextLine();
            System.out.println(controller.searchProductWithoutRange(x, y));
        } catch (Exception e){
            e.printStackTrace();
            menu();
        }
    }

    public void searchProductWithRange() throws IOException {
        try {
            System.out.println("1. Buscar nombre de productos por rango de letras" + "\n" +
                    "2. Buscar productos por rango de precio" + "\n" +
                    "4. Buscar producto por rango de cantidad disponible" + "\n" +
                    "5. Buscar producto por rango de cantidad vendida");
            int x = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa el limite inferios del rango a buscar");
            String y = sc.nextLine();
            System.out.println("Ingresa el limite superior del rango a buscar");
            String z = sc.nextLine();
            System.out.println(controller.searchProductWithRange(x, y, z));
        } catch (Exception e){
            e.printStackTrace();
            menu();
        }
    }

    public void searchOrderWithoutRange() throws IOException {
        try{
            System.out.println("1. Buscar pedido por nombre de cliente" + "\n" +
                    "2. Buscar pedido por precio total" + "\n" +
                    "2. Buscar pedido por fecha de pedido");
        } catch (Exception e){
            e.printStackTrace();
            menu();
        }
    }
    public void searchOrderWithRange() throws IOException {
        try {
            System.out.println("1. Buscar pedido por rango de nombres de compradores" +
                    "2. Buscar pedido por rango de precio total" + "\n" +
                    "3. Buscar pedido por rango de fecha de pedido");
        } catch (Exception e){
            e.printStackTrace();
            menu();
        }
    }
    
}
