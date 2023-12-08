package federicoPoggi.Enteties;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer {
    Random rand=new Random();
    private final int customerId;
    private  String name;
    private String surname;
    private List<Product> cart=new ArrayList<>();

    public Customer(String name, String surname) {
        this.customerId =rand.nextInt(1,200);
        this.name = name;
        this.surname=surname;
        int tier = rand.nextInt(1, 3);
        /*this.cart=cart;*/
    }

    /*----GETTERSE PER OTTENERE DATI CLIENTE---*/

    public int getCustomerId() {
        return customerId;
    }
    public String getName(){
        return this.name;
    }
    public void setProduct (Product single){
        cart.add(single);
    }
    public Product getProd (int index) {
        return cart.get(index);
    }
    public String getSurname(){return surname;}

}
