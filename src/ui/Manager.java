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
        m.menu();
    }

    public void init(){
        try {
            controller.loadProduct();
            controller.loadOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu(){
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
                registerOrder();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                System.out.println("Por favor ingresa una opcion valida");
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

    public void registerOrder(){
        try {
            System.out.println("Estas registrando un pedido \n Ingresa el nombre del cliente");
            String name = sc.nextLine();
            System.out.println("Busca un producto");
            System.out.println("1. Busqueda por rango \n 2. Busqueda sin rango");
            switch (sc.nextInt()){
                case 1:
                    SearchProductWithRange();
                    break;
                case 2:
                    SearchProductWithoutRange();
                    break;
                default:
                    System.out.println("Por favor ingresa una opcion valida");
                    menu();
            }
            System.out.println("Ingresa el id del producto que desea comprar");
            ArrayList<Integer> productos = new ArrayList<>();
            ArrayList<Integer> cantidades = new ArrayList<>();
            while(sc.nextLine().equalsIgnoreCase("Si")) {
                productos.add(sc.nextInt());
                System.out.println("Ingresa la cantidad que desea comprar");
                cantidades.add(sc.nextInt());
                System.out.println("Desear agregar otro producto? Si/No");
            }
            if(controller.registerOrder(name, productos, cantidades)){
                System.out.println("El pedido fue creado con exito");
                controller.saveOrder();
            } else {
                System.out.println("Ha ocurrido un error:(");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SearchProductWithoutRange(){
        try {
            System.out.println("1. Buscar producto por nombre" + "\n" +
                    "2. Buscar producto por categoria" + "\n" +
                    "3. Buscar producto por precio" + "\n" +
                    "4. Buscar producto por cantidad disponible" + "\n" +
                    "5. Buscar producto por cantidad vendida");
            int x = Integer.parseInt(sc.nextLine());
            System.out.println("Ingresa el valor a buscar");
            String y = sc.nextLine();
            System.out.println(controller.searchProduct(x, y));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SearchProductWithRange(){

    }

    
}
