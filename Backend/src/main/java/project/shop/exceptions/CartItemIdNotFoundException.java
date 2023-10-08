package project.shop.exceptions;

public class CartItemIdNotFoundException  extends IllegalArgumentException {
    public CartItemIdNotFoundException(String msg){
        super(msg);
    }
}
