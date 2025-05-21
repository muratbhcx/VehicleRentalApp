package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Cart;
import Vehicle_rental_app.model.CartItem;
import Vehicle_rental_app.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO implements BaseDAO<Cart> {
    @Override
    public long save(Cart cart) {
        return 0;
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public List<Cart> findAll(int page) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    public Cart findByCustomerId(Long customerId) {
        Cart cart = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID)){
                ps.setLong(1, customerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cart = new Cart(rs.getLong("id"),
                            rs.getLong("customer_id"));
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
