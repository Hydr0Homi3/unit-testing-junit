package kubala.testing.cart;

import kubala.testing.order.OrderStatus;
import org.junit.jupiter.api.extension.ExtendWith;


public class CartService {

    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    public Cart processCart(Cart cart) {
        if(cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
        }else {
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
        }
        return cart;
    }
}
