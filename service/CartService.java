package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CartDAO;
import Vehicle_rental_app.dao.CartItemDAO;
import Vehicle_rental_app.model.Cart;
import Vehicle_rental_app.model.CartItem;
import Vehicle_rental_app.model.Customer;
import Vehicle_rental_app.model.Vehicle;

import java.util.List;

public class CartService {

    private CartDAO cartDAO;

    private CartItemDAO cartItemDAO;

    public CartService() {
        cartDAO = new CartDAO();
        cartItemDAO = new CartItemDAO();
    }


    public void addToCart(Customer loginedCustomer, Vehicle vehicle) {
        Cart cart = cartDAO.findByCustomerId(loginedCustomer.getId());

        if (cart == null) {
            cart = new Cart();
        }


        CartItem cartItem = new CartItem();
        cartItem.setVehicle(vehicle);
        cartItem.setCart(cart);

        cartItemDAO.save(cartItem);
        System.out.println("Kiralama Oluşturuldu!");
    }

}
