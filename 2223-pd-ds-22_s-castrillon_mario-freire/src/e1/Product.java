package e1;

public class Product {
    private final int id;
    private final String name;
    private int stock;
    private double price;

    Product(int id,String name,int stock,double price){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    public void increaseStock(int amount){
        stock += amount;
    }

    // Permite reducir el stock de un producto, en caso de pedir más productos de los que hay en stock
    // pone el stock a 0 y devuelve el número de productos que se pueden comprar
    public int decreaseStock(int amount){
        if(amount > stock){
            int a = stock;
            stock = 0;
            return a;
        }else{
            stock -= amount;
            return amount;
        }
    }

    public int getId(){
        return this.id;
    }

    public int getStock(){
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }
}

