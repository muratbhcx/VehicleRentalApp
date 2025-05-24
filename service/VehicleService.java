package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.VehicleDAO;
import Vehicle_rental_app.model.Vehicle;

import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void save(Vehicle vehicle) {

        vehicleDAO.save(vehicle);
        System.out.println("Araç Kaydedildi!");
    }

    public List<Vehicle> getAll(int page) {
        return vehicleDAO.findAll(page);
    }

    public int getTotalPage() {
        return vehicleDAO.findTotalPage();
    }

    public List<Vehicle> search(String SearchVehicleBrand) {
        return vehicleDAO.searchByBrand(SearchVehicleBrand);
    }

    public List<Vehicle> getAllByCategoryName(String categoryName, int page) {
        return vehicleDAO.findAllByCategoryName(categoryName, page);
    }

    public Vehicle getByBrand(String brand, String model) {
        return vehicleDAO.findByBrand(brand, model);
    }
}
