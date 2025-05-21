package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.Customer;
import Vehicle_rental_app.service.CustomerService;
import Vehicle_rental_app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO<Customer> {

    public long save(Customer customer) {

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_SAVE)){
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setInt(4,customer.getAge());
            ps.setString(5,customer.getRole());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Customer customer) {

    }

    public Customer findById(Long id) {
        Customer customer = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public List<Customer> findAll(int page) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void delete(Long id) {

    }

    public boolean existsByEmail(String email) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer FindByEmail(String email) {
        Customer customer = null;
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }
}
