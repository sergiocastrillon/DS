package e1.States;

import java.util.Date;


public class CheckOut implements AbstractOrderState {

    private static final CheckOut instance = new CheckOut();
    private CheckOut(){}

    public static CheckOut getInstance(){
        return instance;
    }



    @Override
    public void pay(Order order,Date date) {
        if(order.getAmount() == 0) return; // Si no hay productos entonces no se puede pagar
        if(date != null){ // Para los test
            order.paymentTime = date;
        }else{ // Obtención del tiempo real
            order.paymentTime = new Date(System.currentTimeMillis());
        }

        order.setState(Paid.getInstance());
        order.log.pay(order);
    }

    @Override
    public void shoppingCart(Order order) {
        order.setState(ShoppingCart.getInstance());
        order.log.shoppingCart(order);
    }

    @Override
    public String screenInfo(Order order) {
        StringBuilder str = new StringBuilder();
        str.append(AbstractOrderState.super.screenInfo(order));
        str.append("Phase: Check Out: ");

        str.append(order.getAmount());
        str.append(" products");
        return str.toString();
    }
}
