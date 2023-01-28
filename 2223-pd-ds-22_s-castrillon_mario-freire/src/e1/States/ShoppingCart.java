package e1.States;

import e1.Product;
import e1.ProductCount;

public class ShoppingCart implements AbstractOrderState {

    private static final ShoppingCart instance = new ShoppingCart();
    private ShoppingCart(){}

    public static ShoppingCart getInstance(){
        return instance;
    }
    @Override
    public void addProduct(Order order, Product product, int amount) {
        if(amount < 0) throw new IllegalArgumentException("No se pueden tener cantidades negativas");
        ProductCount aux = null;
        // Comprobamos que el producto  no está en la cesta
        for(ProductCount e : order.products){
            if(e.isSameProduct(product)){
                aux = e;
                break;
            }
        }
        if(aux != null){ // Producto ya existía
            // si está en la cesta cambiamos la cantidad
            // Importante: Si el producto ya estaba con por ejemplo 2 unidades y hacemos un addProduct de 1,
            // la cantidad pasará a ser 1 en vez de 3
            changeAmount(order, product, amount);
        }else{ // Producto nuevo
            order.welcome = false;
            if(product.getStock() == 0) return; // si no hay stock no se añade
            aux = new ProductCount(product,amount);
            order.products.add(aux);
            order.log.addProduct(order,aux);
        }

    }


    @Override
    public String screenInfo(Order order){
        StringBuilder str = new StringBuilder();
        str.append(AbstractOrderState.super.screenInfo(order));
        if(order.welcome){
            str.append("Phase: Shopping -- Welcome to online shop");
        }else{
            str.append("Phase: Shopping -- ");
            str.append(order.getAmount());
            str.append(" products");
        }
        return str.toString();
    }

    @Override
    public void checkOut(Order order) {
        if(order.getAmount() == 0) return; // No se puede pasar a checkOut sin productos
        order.setState(CheckOut.getInstance());
        order.log.checkOut(order);
    }


}
