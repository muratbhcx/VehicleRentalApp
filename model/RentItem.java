package Vehicle_rental_app.model;

import java.math.BigDecimal;

public class RentItem {

    private Long id;

    private Rent rent;

    private Vehicle vehicle;

    private BigDecimal price;

    public RentItem() {
    }

    public RentItem(Long id, Rent rent, Vehicle vehicle, BigDecimal price) {
        this.id = id;
        this.rent = rent;
        this.vehicle = vehicle;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
