package Vehicle_rental_app.model;

public class CartItem {

    private Long id;

    private Vehicle vehicle;

    private Cart cart;

    public CartItem() {
    }

    public CartItem(Long id, Vehicle vehicle, Cart cart) {
        this.id = id;
        this.vehicle = vehicle;
        this.cart = cart;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
