package ui;

import model.Category;
import model.Controller;

import java.io.IOException;
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
    }

    public void init(){
        try {
            controller.loadOrder();
            controller.loadProduct();
        } catch (IOException e) {
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
        switch (sc.nextInt()){
            case 1:
                break;
            case 2:
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
            double price = sc.nextDouble();
            System.out.println("Ingresa la cantidad disponible del producto");
            int availableAmount = sc.nextInt();
            System.out.println("Ingresa el indice de la categoria a la cual pertenece el producto");
            System.out.println(controller.showCategory());
            int category = sc.nextInt();
            System.out.println("Ingresa el numero de veces que este producto se ha vendido");
            int purchasedTimes = sc.nextInt();
            if ( controller.registerProduct(name, description, price, availableAmount, category, purchasedTimes) ){
                System.out.println("El producto fue creado con exito");
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
            int x = sc.nextInt();
            sc.nextLine();
            String y = sc.nextLine();
            System.out.println(controller.searchProduct(x, y));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    
}
