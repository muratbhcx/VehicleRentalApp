package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.AnkaRentalConstants;
import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Category;
import Vehicle_rental_app.model.Vehicle;
import Vehicle_rental_app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public List<Vehicle> searchByBrand(String SearchVehicleBrand) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_SEARCH_BY_NAME)) {
            ps.setString(1, "%" + SearchVehicleBrand + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getLong("id"));
                v.setBrand(rs.getString("brand"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getInt("year"));
                v.setRentalPrice(rs.getBigDecimal("hourly_rental_price"));
                v.setPrice(rs.getBigDecimal("price"));
                v.setCategory((new Category(rs.getLong("category_id"),
                        rs.getString("category_name"))));
                vehicles.add(v);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public long save(Vehicle vehicle) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_SAVE)) {
            ps.setString(1, vehicle.getBrand());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getYear());
            ps.setBigDecimal(4, vehicle.getRentalPrice());
            ps.setBigDecimal(5, vehicle.getPrice());
            ps.setLong(6, vehicle.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public List<Vehicle> findAll(int page) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_FIND_ALL)) {
            int size = AnkaRentalConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setInt(1, AnkaRentalConstants.PAGE_SIZE);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getLong("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBigDecimal("hourly_rental_price"),
                        rs.getBigDecimal("price"),
                        new Category(rs.getLong("category_id"),
                                rs.getString("category_name"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public int findTotalPage() {
        try(Connection connection = DBUtil.getConnection();
        Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(SqlScriptConstants.VEHICLE_TOTAL_PAGE_COUNT);

            if(rs.next()){
                int totalRows = rs.getInt(1);
                return (int) Math.ceil((double)totalRows/AnkaRentalConstants.PAGE_SIZE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Vehicle> findAllByCategoryName(String categoryName, int page) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_FIND_BY_CATEGORY_NAME)) {
            ps.setString(1,categoryName);
            int size = AnkaRentalConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setInt(2, AnkaRentalConstants.PAGE_SIZE);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getLong("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBigDecimal("hourly_rental_price"),
                        rs.getBigDecimal("price"),
                        new Category(rs.getLong("category_id"),
                                rs.getString("category_name"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;

    }

    public Vehicle findByBrand(String brand, String model) {
        Vehicle vehicle = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_FIND_BY_NAME)) {
            ps.setString(1,brand);
            ps.setString(2,model);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicle = (new Vehicle(rs.getLong("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBigDecimal("hourly_rental_price"),
                        rs.getBigDecimal("price"),
                        new Category(rs.getLong("category_id"),
                                rs.getString("category_name"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
