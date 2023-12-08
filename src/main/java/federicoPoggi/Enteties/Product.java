package federicoPoggi.Enteties;

import java.util.Random;

public class Product {
    Random random=new Random();
    private long idProduct;
    private String nameProduct;
    private String category;
    private double price;

    public Product(String prodotto, double price,String categoria){
        this.idProduct= random.nextLong(1,10000);
        this.nameProduct=prodotto;
        this.price=price;
        this.category=categoria;
    }


    /*GETTERS AND SETTERS*/

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCategory() {
        return this.category=category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{"+getNameProduct()+" | "+getPrice()+" | "+getIdProduct()+" | "+ getCategory() +" }";
    }
}
