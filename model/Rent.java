package Vehicle_rental_app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Rent extends BaseModel {

    private Customer customer;

    private BigDecimal totalAmount;

    private LocalDateTime rentDate;

    private List<RentItem> rentItems;

    public Rent() {
        this.rentDate = LocalDateTime.now();
    }

    public Rent(Long id) {
        this.setId(id);
    }

    public Rent(Customer customer, BigDecimal totalAmount, LocalDateTime rentDate) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.rentDate = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public List<RentItem> getRentItems() {
        return rentItems;
    }

    public void setRentItems(List<RentItem> rentItems) {
        this.rentItems = rentItems;
    }
}
