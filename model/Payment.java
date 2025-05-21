package Vehicle_rental_app.model;

import Vehicle_rental_app.model.enums.PaymentMethod;

import java.math.BigDecimal;

public class Payment extends BaseModel{

    private Rent rent;

    private PaymentMethod paymentMethod;

    private BigDecimal amount;

    public Payment(Rent rent, PaymentMethod paymentMethod) {
        this.rent = rent;
        this.paymentMethod = paymentMethod;
        this.amount = rent.getTotalAmount();
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
