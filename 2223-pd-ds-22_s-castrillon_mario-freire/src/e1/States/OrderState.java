package e1;

import e1.States.Order;

import java.util.Date;


public interface OrderState {

    default String screenInfo(Order order){
        return "Order number: " +
                order.getId() +
                "\n";
    }
    default void addProduct(Order order, Product product, int amount){

    }
    default void changeAmount(Order order, Product product, int amount){
    }

    default void deleteProduct(Order order,Product product){

    }
    default void checkOut(Order order){}
    default void pay(Order order, Date date) {}
    default void shoppingCart(Order order){}
    default void cancel(Order order){}
    default void complete(Order order){}

}
