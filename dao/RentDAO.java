package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Customer;
import Vehicle_rental_app.model.Rent;
import Vehicle_rental_app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {

    public void save(Rent rent) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_SAVE)) {
            ps.setLong(1, rent.getCustomer().getId());
            ps.setString(2, rent.getVehicleBrand());
            ps.setString(3, rent.getVehicleModel());
            ps.setBigDecimal(4, rent.getTotalAmount());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    public List<Rent> findAllByCustomerId(Long customerId) {
        List<Rent> rents = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_FIND_BY_CUSTOMER_ID)) {

            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                Rent rent = new Rent();
                rent.setId(rs.getLong("id"));
                rent.setCustomer(new Customer(rs.getLong("customer_id")));
                rent.setVehicleBrand(rs.getString("vehicle_brand"));
                rent.setVehicleModel(rs.getString("vehicle_model"));
                rent.setTotalAmount(rs.getBigDecimal("total_amount"));
                rents.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rents;
    }
}