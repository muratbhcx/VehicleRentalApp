package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.RentItemDAO;
import Vehicle_rental_app.model.RentItem;

import java.util.List;

public class RentItemsService {

    private final RentItemDAO rentItemDAO;

    public RentItemsService() {
        this.rentItemDAO = new RentItemDAO();
    }

    public void save(List<RentItem> rentItems){
        rentItemDAO.saveAll(rentItems);
        System.out.println("Kiralanan Araç Kaydedildi");
    }
}
