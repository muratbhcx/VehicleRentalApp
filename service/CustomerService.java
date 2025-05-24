package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.CustomerDAO;
import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.Customer;
import Vehicle_rental_app.util.PasswordUtil;

public class CustomerService {

    private final CustomerDAO customerDAO;


    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public Customer login(String email, String password) throws AnkaRentalException {
        boolean isExist = customerDAO.existsByEmail(email);
        if (!isExist) {
            throw new AnkaRentalException(ExceptionMessagesConstans.CUSTOMER_EMAIL_DOES_NOT_EXIST);
        }
        String hashedPassword = PasswordUtil.hash(password);
        Customer foundCustomer = customerDAO.FindByEmail(email);
        if (foundCustomer != null) {
            boolean passwordEquals = foundCustomer.getPassword().equals(hashedPassword);
            if (!passwordEquals) {
                throw new AnkaRentalException(ExceptionMessagesConstans.CUSTOMER_PASSWORDS_DOES_NOT_MATCH);
            }else{
                System.out.println("Sisteme Giriş Başarılı!");
            }

        }
        return foundCustomer;
    }

    public void save(String name, int age, String email, String password, String role) throws AnkaRentalException {
        boolean isExist = customerDAO.existsByEmail(email);
        if (isExist) {
            throw new AnkaRentalException(ExceptionMessagesConstans.CUSTOMER_EMAIL_ALREADY_EXIST);
        }
        Customer customer = new Customer(name, age, email, PasswordUtil.hash(password), role);
        customerDAO.save(customer);
        System.out.println("Müşteri Sisteme Kaydedildi!");
    }
}
