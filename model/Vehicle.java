package Vehicle_rental_app.model;

import java.math.BigDecimal;

public class Vehicle {

    private long id;

    private String brand;

    private String model;

    private int year;

    private BigDecimal rentalPrice;

    private BigDecimal price;

    private Category category;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int year, BigDecimal rentalPrice, BigDecimal price, Category category) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.price = price;
        this.category = category;
    }

    public Vehicle(Long id, String brand, String model, int year, BigDecimal rentalPrice, BigDecimal price, Category category) {
        this.setId(id);
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.price = price;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
