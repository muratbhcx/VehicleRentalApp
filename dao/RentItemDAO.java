package Vehicle_rental_app.dao;

import Vehicle_rental_app.dao.constants.SqlScriptConstants;
import Vehicle_rental_app.model.RentItem;
import Vehicle_rental_app.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class RentItemDAO implements BaseDAO<RentItem> {


    public void saveAll(List<RentItem> rentItems) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_ITEMS_SAVE)) {
            for (RentItem rentItem : rentItems) {
                ps.setLong(1, rentItem.getVehicle().getId());
                ps.setLong(2, rentItem.getVehicle().getId());
                ps.setBigDecimal(3, rentItem.getPrice());
                ps.addBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long save(RentItem rentItem) {
        return 0;
    }

    @Override
    public void update(RentItem rentItem) {

    }

    @Override
    public RentItem findById(Long id) {
        return null;
    }

    @Override
    public List<RentItem> findAll(int page) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
