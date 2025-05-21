package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Payment;
import Vehicle_rental_app.util.DBUtil;

import java.sql.*;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {

    public long save(Payment payment) {
        long id = 0;
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE);
            ps.setLong(1, payment.getRent().getId());
            ps.setString(2,payment.getPaymentMethod().name());
            ps.setBigDecimal(3,payment.getAmount());
            ps.executeUpdate();

            ResultSet rs =  ps.getGeneratedKeys();

            if (rs.next()) {
            id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public List<Payment> findAll(int page) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}