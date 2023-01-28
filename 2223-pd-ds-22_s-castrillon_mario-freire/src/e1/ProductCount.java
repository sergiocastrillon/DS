package e1;

public class ProductCount {
    private final Product product;
    private int amount;

    public ProductCount(Product product, int amount){
        this.product = product;
        this.amount = product.decreaseStock(amount);
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId(){
        return product.getId();
    }

    public int getAmount() {
        return amount;
    }

    // Cambia la cantidad de un mismo producto, devuelve amount que es la cantidad que se quiere comprar.
    // Tener en cuenta que si se supera el stock de un producto, la cantidad solo será la máxima disponible.
    public void changeAmount(int amount){
        if(this.amount >= amount){
            product.increaseStock(this.amount - amount);
            this.amount = amount;
        }
        else{ // decreaseStock devuelve la cantidad que varía asi que se la sumamos a amount
            this.amount += product.decreaseStock(amount - this.amount);
        }
    }

    public boolean isSameProduct(Product product){
        // Como se espera que no se creen objectos clones (sobretodo de cara a manejar correctamente el stock)
        // podemos comparar con el equals por defecto aunque solo compruebe si dos variables hacen referencia
        // al mismo objeto
        return this.product.equals(product);
    }
}
