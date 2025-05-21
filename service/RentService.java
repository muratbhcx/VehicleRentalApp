package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CartItemDAO;
import Vehicle_rental_app.dao.RentDAO;
import Vehicle_rental_app.model.*;
import Vehicle_rental_app.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentService {

    private final RentDAO rentDAO;

    private final CartItemService cartItemService;

    private final PaymentService paymentService;

    private final RentItemsService rentItemsService;


    public RentService() {
        this.rentDAO = new RentDAO();
        this.cartItemService = new CartItemService();
        this.paymentService = new PaymentService();
        this.rentItemsService = new RentItemsService();
    }

    public Rent save(Customer customer, PaymentMethod paymentMethod) {
        List<CartItem> cartItems = cartItemService.findByCustomerId(customer.getId());
        BigDecimal totalAmount = BigDecimal.ZERO;
        cartItems.forEach(
                cartItem -> {
                    BigDecimal amount = new BigDecimal(cartItem.getVehicle().getPrice().intValue());
                    totalAmount.add(amount);
                }
        );



        Rent rent = new Rent();
        rent.setCustomer(customer);
        rent.setTotalAmount(totalAmount);
        rent.setRentDate(LocalDateTime.now());
        long rentId = rentDAO.save(rent);

        List<RentItem> rentItems = new ArrayList<>();

        cartItems.forEach(cartItem -> {
            RentItem rentItem = new RentItem();
            rentItem.setRent(new Rent(rentId));
            rentItem.setVehicle(new Vehicle(cartItem.getVehicle().getId()));
            rentItem.setPrice(cartItem.getVehicle().getPrice());
            rentItems.add(rentItem);
        });

        rentItemsService.save(rentItems);

        rentDAO.save(rent);
        paymentService.save(rent, paymentMethod);
        System.out.println("Kiralama İşlemi Başarıyla Tamamlandı!");
        return rent;


    }

    public List<Rent> getAllByCustomer(Customer loginedCustomer) {
        return rentDAO.findAllByCustomerId(loginedCustomer.getId());
    }
}
