package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.VehicleDAO;
import Vehicle_rental_app.model.User;
import Vehicle_rental_app.model.Vehicle;

import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void save(Vehicle vehicle, User user) {

        vehicle.setCreatedUser(user);
        vehicle.setUpdatedUser(user);

        vehicleDAO.save(vehicle);
        System.out.println("Araç Kaydedildi!");
    }

    public List<Vehicle> getAll(int page) {
        return vehicleDAO.findAll(page);
    }

    public void deleteById(long id) {
        vehicleDAO.delete(id);
        System.out.println("Ürün Silindi!");
    }

    public int getTotalPage() {
        return vehicleDAO.findTotalPage();
    }

    public List<Vehicle> search(String searchVehicleName) {
        return vehicleDAO.searchByName(searchVehicleName);
    }

    public List<Vehicle> getAllByCategoryName(String categoryName) {
        return vehicleDAO.findAllByCategoryName(categoryName);
    }

    public Vehicle getByName(String brand) {
        return vehicleDAO.findByName(brand);
    }
}
