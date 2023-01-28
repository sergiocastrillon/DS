package e1;

import e1.States.Order;

public class Log {
    private final StringBuilder l = new StringBuilder();
    private String state = "Shopping Cart";

    public Log(Order order){
        l.append("Order ");
        l.append(order.getId());
        l.append(": Shopping Phase\n");
    }

    public void addProduct(Order order, ProductCount product){
        l.append("- Add: Item: ");
        l.append(product.getProductId());
        l.append(" - Quantity: ");
        l.append(product.getAmount());
        l.append(" -> Shopping Cart -- Products: ");
        l.append(order.getAmount());
        l.append("\n");
    }

    public void changeAmount(Order order, ProductCount product){
        l.append("- Modify: Item: ");
        l.append(product.getProductId());
        l.append(" - Quantity: ");
        l.append(product.getAmount());
        l.append(" -> ");
        l.append(state);
        l.append(" -- Products: ");
        l.append(order.getAmount());
        l.append("\n");
    }

    public void deleteProduct(Order order, ProductCount product){
        l.append("- Remove: Item: ");
        l.append(product.getProductId());
        l.append(" -> ");
        l.append(state);
        l.append(" -- Products: ");
        l.append(order.getAmount());
        l.append("\n");
    }

    public void shoppingCart(Order order){
        state = "Shopping Cart";
        l.append("Order ");
        l.append(order.getId());
        l.append(": Shopping Phase\n");
    }

    public void checkOut(Order order){
        state = "CheckOut Order";
        l.append("Order ");
        l.append(order.getId());
        l.append(": Check Out Phase\n");
    }

    public void pay(Order order){
        state = "Paid";

        l.append("Order ");
        l.append(order.getId());
        l.append(": Payment Phase\n");
    }

    public void cancel(Order order){
        state = "Cancelled";

        l.append("Order ");
        l.append(order.getId());
        l.append(": Cancelled Order");
    }

    public void complete(Order order){
        state = "Completed";

        l.append("Order ");
        l.append(order.getId());
        l.append(": Completed Order");
    }

    public String obtainLog(){
        return l.toString();
    }


}
