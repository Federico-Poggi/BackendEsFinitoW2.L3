package federicoPoggi;


import com.github.javafaker.Faker;
import federicoPoggi.Enteties.Customer;
import federicoPoggi.Enteties.Orders;
import federicoPoggi.Enteties.Product;

import javax.annotation.processing.SupportedSourceVersion;
import java.security.Key;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Application {
    static List<Customer> listOfCustomer = new ArrayList<>();
    static List<Product> listOfProduct = new ArrayList<>();
    static Map<String, List<Product>> listOfOrders = new HashMap<>();

    public static void main(String[] args) {
        Random rand = new Random();
        createCustomer();
        createProd();
        List<String> key = listOfCustomer.stream().map(Customer::getName).toList();
        System.out.println("\n");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< -CLIENTI- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        key.forEach(c-> System.out.println("Cliente: " + c));


        Product product1;
        Product product2;
        Product product3;
        for (int i = 0; i < key.size(); i++) {
            product1 = listOfProduct.get(rand.nextInt(0, listOfProduct.size()));
            do {
                product2 = listOfProduct.get(rand.nextInt(0, listOfProduct.size()));
                product3 = listOfProduct.get(rand.nextInt(0, listOfProduct.size()));
            } while ((product3 != product2 && product3 != product1 && product2!=product1));
            addOrders(listOfCustomer.get(i), product1, product2, product3);
        }
        System.out.println("\n");
        //ho stampato gli ordini di tutti i clienti
        System.out.println("------------------ORDINI CLIENTI-------------------");
        for (String customer: listOfOrders.keySet()){
            List<Product> listaOrdini= listOfOrders.get(customer).stream().toList();
            listaOrdini.forEach(product -> System.out.println( customer+":"+" | "+ product.toString()));
        }
        System.out.println("\n");
        System.out.println("-----------------PRODOTTI BOOKS CHE COSTANO PIU DI 100-------------------------");

        List<Product> listBooksPriceMoreThanCento=listOfProduct.stream().filter(product -> product.getPrice()>100 && product.getCategory().equals("Books")).toList();
        listBooksPriceMoreThanCento.forEach(product -> System.out.println("Il libro: "+ product.getNameProduct()+
                                            " ha un prezzo maggiore di 100, infatti costa "+product.getPrice()+"€"));
        System.out.println("\n");

        System.out.println("-------------------CATEGORIA BABY----------------------");

        List<Product> listBabyProd=listOfProduct.stream().filter(product -> product.getCategory().equals("Baby")).toList();
        listBabyProd.forEach(product -> System.out.println("CATEGORIA BAMBINI: " + product.getNameProduct()));
        System.out.println("\n");

        System.out.println("-------------------CATEGORIA BOYS+SCONTO 10%----------------------");

        List<Product> listBoysandSconto=listOfProduct.stream().filter(product -> product.getCategory().equals("Boys"))
                .map(product -> {
                    product.setPrice(product.getPrice() * 0.90);
                            return product;
                }).toList();
        listBoysandSconto.forEach(System.out::println);
    }


    static void createCustomer() {
        Supplier<Customer> customerSupplier = () -> {
            Faker faker = new Faker(Locale.ITALIAN);
            return new Customer(faker.pokemon().name(), faker.ancient().god());
        };

        for (int i = 0; i < 10; i++) {
            listOfCustomer.add(customerSupplier.get());
            System.out.println();
        }
        listOfCustomer.forEach(customer -> System.out.println("CUSTOMER NAME: " + customer.getName() + " | " + "CUSTOMER SURNAME: " + customer.getSurname() +
                " | " + "CUSTOMER ID: " + customer.getCustomerId()));

    }

    static void createProd() {
        /*--------BOOKS------------*/
        Product lordOfTheRing = new Product("Il Signore degli Anelli (trilogia)", 39.99, "Books");
        Product recipieBook = new Product("La cucina italiana", 24.95, "Books");
        Product daVinciCode = new Product("Il codice da vinci", 101.99, "Books");
        /*--------BABY-------------*/
        Product giostrinaMusicale = new Product("Giostra musicale", 39.95, "Baby");
        Product pampers = new Product("Pannolini", 19.99, "Baby");
        Product proiettoreStelle = new Product("Proiettore Stellare", 129.99, "Baby");
        /*-------BOYS--------------*/
        Product lego = new Product("Lego-Ford Mustang Shelby 500", 50.99, "Boys");
        Product robotDaCostruire = new Product("Tech Robot Building-Scala 1:20", 130.99, "Boys");
        Product skateboard = new Product("Skateboard Extreme", 70.99, "Boys");

        Product[] arrayProd;
        arrayProd = new Product[9];
        arrayProd = new Product[]{lordOfTheRing, daVinciCode, recipieBook, giostrinaMusicale, pampers, proiettoreStelle, lego, robotDaCostruire, skateboard};
        listOfProduct.addAll(Arrays.asList(arrayProd));
        listOfProduct.forEach(product -> System.out.println("ID Prodotto: " + product.getIdProduct() + " " + "| Prodotto: " + product.getNameProduct() + " " + "| Prezzo: " + product.getPrice() + "€"+
                "| CATEGORIA: "+product.getCategory()));
    }

    static void addOrders(Customer customer, Product product, Product prod2, Product prod3) {
        List<Product> cart = new ArrayList<>();
        cart.add(product);
        cart.add(prod2);
        cart.add(prod3);
        listOfOrders.put(customer.getName(), cart);
        /*listOfOrders.forEach((customer1, products) -> System.out.println(customer1.getName()+" "+ products) );*/
    }
    static double sconto(double prezzoint){
        double scontoDaApplicare=(prezzoint*10)/100;
        return scontoDaApplicare;
    }
}

