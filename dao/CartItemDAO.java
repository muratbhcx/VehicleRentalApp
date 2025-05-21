package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Cart;
import Vehicle_rental_app.model.CartItem;
import Vehicle_rental_app.model.Vehicle;
import Vehicle_rental_app.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {



    public void save(CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_SAVE)) {
            ps.setLong(1, cartItem.getCart().getId());
            ps.setLong(2, cartItem.getVehicle().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
