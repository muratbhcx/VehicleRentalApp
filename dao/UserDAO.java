package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.User;
import Vehicle_rental_app.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    public long save(User user) {
        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.USER_SAVE))
        {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.getActive());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User findByUsername(String username) {
        User user = null;

        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.USER_FIND_BY_NAME)){
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        user = new User();
        while(rs.next()){
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setActive(rs.getBoolean("active"));

        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
