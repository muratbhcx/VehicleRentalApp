package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.UserDAO;
import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.User;
import Vehicle_rental_app.util.PasswordUtil;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {

        userDAO = new UserDAO();}

    public void save(String username, String password) throws AnkaRentalException {
        User foundUser = userDAO.findByUsername(username);

        if (foundUser == null) {
            throw new AnkaRentalException(ExceptionMessagesConstans.USER_EMAIL_ALREADY_EXIST);
        }
        userDAO.save(new User(username, PasswordUtil.hash(password)));
        System.out.println("Kayıt Başarılı");
    }

    public User login(String username, String password) throws AnkaRentalException {
        User foundUser = userDAO.findByUsername(username);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equals(foundUser.getPassword())) {
                throw new AnkaRentalException(ExceptionMessagesConstans.USER_PASSWORDS_DOES_NOT_MATCH);
            }
        } else {
            throw new AnkaRentalException(ExceptionMessagesConstans.USER_PASSWORDS_DOES_NOT_MATCH);
        }
        System.out.println("Giriş Başarılı!");
        return foundUser;
    }

}
