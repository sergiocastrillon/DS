package e1.States;

import e1.OrderState;

public class Cancelled implements OrderState {
    private static final Cancelled instance = new Cancelled();
    private Cancelled(){}

    public static Cancelled getInstance(){
        return instance;
    }

    @Override
    public String screenInfo(Order order) {
        return OrderState.super.screenInfo(order) +
                "Phase: Cancelled Order";
    }
}
