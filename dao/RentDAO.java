package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Rent;
import Vehicle_rental_app.model.RentItem;
import Vehicle_rental_app.model.Vehicle;
import Vehicle_rental_app.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentDAO implements BaseDAO<Rent> {

    public long save(Rent rent) {
        long generatedId = 0;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, rent.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(rent.getRentDate()));
            ps.setBigDecimal(3, rent.getTotalAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;

    }

    @Override
    public void update(Rent rent) {

    }

    @Override
    public Rent findById(Long id) {
        return null;
    }

    @Override
    public List<Rent> findAll(int page) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    public List<Rent> findAllByCustomerId(Long customerId) {
        List<Rent> rents = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_FIND_BY_CUSTOMER_ID)) {

            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                long rentId = rs.getLong("rent_id");

                Rent rent = new Rent();
                rent.setId(rentId);
                rent.setRentDate(LocalDateTime.parse(rs.getTimestamp("rent_date").toString()));
                rent.setRentItems(new ArrayList<>());

                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getLong("vehicle_id"));
                vehicle.setBrand(rs.getString("vehicle_brand"));

                RentItem rentItem = new RentItem();
                rentItem.setRent(rent);
                rentItem.setVehicle(vehicle);
                rentItem.setPrice(rs.getBigDecimal("price"));

                rent.getRentItems().add(rentItem);

                rents.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rents;
    }
}