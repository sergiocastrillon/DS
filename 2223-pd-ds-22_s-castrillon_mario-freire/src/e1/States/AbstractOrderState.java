package e1.States;

import e1.OrderState;
import e1.Product;
import e1.ProductCount;

public interface AbstractOrderState extends OrderState {
    @Override
    // A changeAmount se le pasa la orden, el producto y la cantidad que se quiere de ese producto
    // (no la diferencia con la cantidad actual)
    default void changeAmount(Order order, Product product, int amount) {
        if(amount < 0) throw new IllegalArgumentException("No se pueden tener cantidades negativas");
        for(ProductCount e : order.products){
            if(e.isSameProduct(product)){
                if(amount == 0){ // Si 0 borrar producto
                    e.changeAmount(0);
                    order.products.remove(e);
                    order.log.deleteProduct(order,e);
                }else{
                    e.changeAmount(amount);
                }
                order.log.changeAmount(order,e);
            }
        }
    }

    @Override
    default void deleteProduct(Order order, Product product) {
        for(ProductCount p : order.products) {
            if(p.isSameProduct(product)) {
                p.changeAmount(0);
                order.products.remove(p);
                order.log.deleteProduct(order,p);
                return;
            }
        }
    }
}
