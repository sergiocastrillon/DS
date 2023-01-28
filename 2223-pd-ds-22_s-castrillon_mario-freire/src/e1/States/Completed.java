package e1.States;

import e1.OrderState;

public class Completed implements OrderState {
    private static final Completed instance = new Completed();
    private Completed(){}

    public static Completed getInstance(){
        return instance;
    }

    @Override
    public String screenInfo(Order order) {
        StringBuilder str = new StringBuilder();
        str.append(OrderState.super.screenInfo(order));
        str.append("Phase: Completed Order: ");

        str.append(order.getAmount());
        str.append(" products");
        return str.toString();
    }
}
