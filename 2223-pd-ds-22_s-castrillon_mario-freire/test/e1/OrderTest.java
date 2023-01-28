package e1;

import e1.States.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {
    Order order1 = new Order(1);
    Order order2 = new Order(2);
    Order order3 = new Order(3);
    Order order1234 = new Order(1234);


    Date d1 = new Date();
    Date d2 = new Date();


    @BeforeEach
    void setDates() throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        d1 = f.parse("10-12-2022 18:02:43");
        d2 = f.parse("11-12-2022 05:00:12");
    }
    @Test
    void testInfoLog()  {

        Product p1 = new Product(1,"PS5",10,500);
        Product p2 = new Product(2,"The Last Of US: Part II",17,65.54);
        Product p3 = new Product(3,"Tomates",30,0.66);
        Product p4 = new Product(4,"KitKat",40,1.43);
        Product p5 = new Product(5,"Haribo",23,1.56);

        assertEquals("""
                Order number: 1
                Phase: Shopping -- Welcome to online shop""",order1.screenInfo());
        order1.addProduct(p1,2);
        order1.addProduct(p2,2);
        order1.addProduct(p4,5);

        assertEquals("""
                Order number: 1
                Phase: Shopping -- 3 products""",order1.screenInfo());

        order1.checkOut();

        order1.changeAmount(p1,1);
        order1.changeAmount(p4,8);
        order1.deleteProduct(p2);

        assertEquals("""
                Order number: 1
                Phase: Check Out: 2 products""",order1.screenInfo());


        order1.shoppingCart();
        order1.addProduct(p5,15);
        order1.addProduct(p2,1);


        assertEquals("""
                Order number: 1
                Phase: Shopping -- 4 products""",order1.screenInfo());

        order1.checkOut();
        order1.pay(d1);

        assertEquals("""
                Order number: 1
                Phase: Paid Order: 4 products -- date 2022-12-10 18:02:43""",order1.screenInfo());


        order1.complete();

        assertEquals("""
                Order number: 1
                Phase: Completed Order: 4 products""",order1.screenInfo());


        assertEquals("""
                Order 1: Shopping Phase
                - Add: Item: 1 - Quantity: 2 -> Shopping Cart -- Products: 1
                - Add: Item: 2 - Quantity: 2 -> Shopping Cart -- Products: 2
                - Add: Item: 4 - Quantity: 5 -> Shopping Cart -- Products: 3
                Order 1: Check Out Phase
                - Modify: Item: 1 - Quantity: 1 -> CheckOut Order -- Products: 3
                - Modify: Item: 4 - Quantity: 8 -> CheckOut Order -- Products: 3
                - Remove: Item: 2 -> CheckOut Order -- Products: 2
                Order 1: Shopping Phase
                - Add: Item: 5 - Quantity: 15 -> Shopping Cart -- Products: 3
                - Add: Item: 2 - Quantity: 1 -> Shopping Cart -- Products: 4
                Order 1: Check Out Phase
                Order 1: Payment Phase
                Order 1: Completed Order""",order1.obtainLog());
    }


    @Test
    void testStockOperations(){

        Product p1 = new Product(1,"PS5",5,500);
        Product p2 = new Product(2,"The Last Of US: Part II",10,65.54);
        Product p3 = new Product(3,"Tomates",7,0.66);
        Product p4 = new Product(4,"KitKat",10,1.43);
        Product p5 = new Product(5,"Haribo",30,1.56);

        order2.addProduct(p1,6);

        assertEquals(5,order2.getProductAmount(p1));
        assertEquals(0,p1.getStock());

        order2.deleteProduct(p1);

        assertEquals(5,p1.getStock());

        order2.addProduct(p2,5);

        assertEquals(5,p2.getStock());

        order2.changeAmount(p2,11);

        assertEquals(10,order2.getProductAmount(p2));
        assertEquals(0,p2.getStock());

        order2.changeAmount(p2,2);

        assertEquals(2,order2.getProductAmount(p2));
        assertEquals(8,p2.getStock());

        order3.addProduct(p2,6);
        assertEquals(2,p2.getStock());

        order2.deleteProduct(p2);
        order3.changeAmount(p2,8);

        assertEquals(2,p2.getStock());

    }

    @Test
    void testImposibleOperations(){

        Product p1 = new Product(1,"PS5",5,500);
        Product p2 = new Product(2,"T",5,500);

        assertEquals("""
                Order number: 1234
                Phase: Shopping -- Welcome to online shop""",order1234.screenInfo());

        order1234.pay(d1);

        assertEquals("""
                Order number: 1234
                Phase: Shopping -- Welcome to online shop""",order1234.screenInfo());


        order1234.cancel();
        order1234.complete();

        assertEquals("""
                Order number: 1234
                Phase: Shopping -- Welcome to online shop""",order1234.screenInfo());

        order1234.checkOut(); // Imposible pasar a checkout sin productos

        assertEquals("""
                Order number: 1234
                Phase: Shopping -- Welcome to online shop""",order1234.screenInfo());


        order1234.addProduct(p1,4);

        order1234.checkOut();

        order1234.cancel();
        order1234.complete();

        assertEquals("""
                Order number: 1234
                Phase: Check Out: 1 products""",order1234.screenInfo());

        order1234.addProduct(p2,1);

        assertEquals(1,order1234.getAmount());

        order1234.pay(d2);

        order1234.deleteProduct(p1);
        assertEquals(1,order1234.getAmount());
        order1234.addProduct(p2,1);
        assertEquals(1,order1234.getAmount());
        order1234.changeAmount(p1,2);
        assertEquals(4,order1234.getProductAmount(p1));

        // Check adicional del stock
        assertEquals(1,p1.getStock());

        order1234.cancel();
        assertEquals(5,p1.getStock());
        assertEquals(0,order1234.getAmount());

        assertEquals("""
                Order number: 1234
                Phase: Cancelled Order""",order1234.screenInfo());


    }

}