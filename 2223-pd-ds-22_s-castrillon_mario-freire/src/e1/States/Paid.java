package e1.States;

import e1.OrderState;

import java.text.SimpleDateFormat;

public class Paid implements OrderState {
    // Paid representa un producto pagado, es decir que pay() que se aplica desde checkOut realizaría el pago
    private static final Paid instance = new Paid();
    private Paid(){}

    public static Paid getInstance(){
        return instance;
    }

    // Una vez pasan las 24 horas desde que se ha pagado se llama al método para pasarlo a completado
    @Override
    public void complete(Order order) {
        order.setState(Completed.getInstance());
        order.log.complete(order);
    }

    @Override
    public void cancel(Order order) {
        order.setState(Cancelled.getInstance());
        order.log.cancel(order);

        // Eliminar todos los productos reservados
        while(!order.products.isEmpty()){
            order.products.get(0).changeAmount(0);
            order.products.remove(0);
        }
    }

    @Override
    public String screenInfo(Order order) {
        // Conversión de time a String
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = f.format(order.paymentTime);

        StringBuilder str = new StringBuilder();
        str.append(OrderState.super.screenInfo(order));
        str.append("Phase: Paid Order: ");

        str.append(order.getAmount());
        str.append(" products -- date ");
        str.append(time);
        return str.toString();
    }
}
