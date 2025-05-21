package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CartItemDAO;
import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Cart;
import Vehicle_rental_app.model.CartItem;
import Vehicle_rental_app.model.Customer;
import Vehicle_rental_app.model.Vehicle;
import Vehicle_rental_app.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemService {

    private CartItemDAO cartItemDAO;

    public CartItemService() {
        this.cartItemDAO = new CartItemDAO();
    }

    public List<CartItem> findByCustomerId(Long customerId) {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_FIND_BY_CUSTOMER_ID)) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long cartItemId = rs.getLong("cart_item_id");
                Long vehicleId = rs.getLong("vehicle_id");
                String vehicleBrand = rs.getString("vehicle_brand");
                BigDecimal price = rs.getBigDecimal("price");

                Vehicle vehicle = new Vehicle(vehicleId, vehicleBrand, price);
                cartItems.add(new CartItem(cartItemId, vehicle, new Cart()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
