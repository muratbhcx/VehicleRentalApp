package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.PaymentDAO;
import Vehicle_rental_app.model.Payment;
import Vehicle_rental_app.model.Rent;
import Vehicle_rental_app.model.enums.PaymentMethod;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment save(Rent rent, PaymentMethod paymentMethod){
        Payment payment = new Payment(rent, paymentMethod);
        paymentDAO.save(payment);
        return payment;
    }

}

