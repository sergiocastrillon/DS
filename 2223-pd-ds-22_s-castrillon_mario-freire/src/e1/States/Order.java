package e1.States;

import e1.Log;
import e1.OrderState;
import e1.Product;
import e1.ProductCount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private final int id;
    Date paymentTime = null;

    List<ProductCount> products = new ArrayList<>();
    private OrderState state;
    Log log;
    boolean welcome = true;

    public Order(int id){
        this.id = id;
        this.state = ShoppingCart.getInstance();
        this.log = new Log(this);
    }

    public String screenInfo() {
        return state.screenInfo(this);
    }
    void setState(OrderState state) {
        this.state = state;
        welcome = false;
    }

    public int getAmount(){
        return products.size();
    }
    
    public int getProductAmount(Product product){
        for(ProductCount p : products){
            if(p.isSameProduct(product)) return p.getAmount();
        }
        return 0;
    }

    public int getId(){
        return id;
    }

    public void addProduct(Product product, int amount){
        state.addProduct(this,product,amount);
    }

    public void changeAmount(Product product, int amount){
        state.changeAmount(this,product,amount);
    }

    public void deleteProduct(Product product){
        state.deleteProduct(this,product);
    }

    public void shoppingCart(){
        state.shoppingCart(this);
    }

    public void checkOut(){
        state.checkOut(this);
    }

    public void pay(Date date) {
        state.pay(this,date);
    }
    public void cancel(){
        state.cancel(this);
    }
    public void complete(){
        state.complete(this);
    }

    public String obtainLog(){
        return log.obtainLog();
    }
}
