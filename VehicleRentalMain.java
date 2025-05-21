package Vehicle_rental_app;

import Vehicle_rental_app.dao.UserDAO;
import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.*;
import Vehicle_rental_app.model.enums.PaymentMethod;
import Vehicle_rental_app.service.*;
import Vehicle_rental_app.util.PasswordUtil;

import java.lang.invoke.StringConcatException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VehicleRentalMain {

    private static User LOGINED_USER;

    private static Customer LOGINED_CUSTOMER;

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();

    private static final CategoryService categoryService = new CategoryService();

    private static final VehicleService vehicleService = new VehicleService();

    private static final CartService cartService = new CartService();

    private static final RentService rentService = new RentService();

    private static final CartItemService cartItemService = new CartItemService();

    public static void main(String[] args) {


        while (true) {
            System.out.println("-----ANKA RENT-----");
            System.out.println("1- Kayıt Ol");
            System.out.println("2- Giriş Yap");
            System.out.println("0- Çıkış");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();
            try {
                switch (choise) {
                    case 1:
                        signUpMenu();
                        break;
                    case 2:
                        loginMenu();
                        break;
                    case 0:
                        System.out.println("Çıkış Yapılıyor...");
                        return;
                    default:
                        System.out.println("Geçersiz İşlem!");
                }
            } catch (AnkaRentalException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    private static void loginMenu() throws AnkaRentalException {
        while (true) {
            System.out.println("-----Giriş Yap-----");
            System.out.println("1- Müşteri Girişi");
            System.out.println("2- Kurumsal Müşteri Girişi");
            System.out.println("3- Kullanıcı Girişi(ADMİN)");
            System.out.println("0- Geri Dön");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    loginCustomer();
                    break;
                case 2:
                    logincrprtCustomer();
                    break;
                case 3:
                    loginUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz İşlem!");


            }
        }
    }

    private static void logincrprtCustomer() {

    }

    private static void loginUser() throws AnkaRentalException {
        System.out.print("Kullanıcı Adı: ");
        String username = scanner.next();
        System.out.print("Şifre: ");
        String password = scanner.next();

        User loginedUser = userService.login(username, password);
        if (loginedUser != null && loginedUser.getActive()) {
            LOGINED_USER = loginedUser;
            getLoginedUserMenu();



        } else {
            throw new AnkaRentalException(ExceptionMessagesConstans.USER_IS_NOT_ACTIVE);
        }

    }

    private static void getLoginedUserMenu() throws AnkaRentalException {

        while (true) {
            System.out.println("-----Admin Menü-----");
            System.out.println("1- Kategori Oluştur");
            System.out.println("2- Kategori Listele");
            System.out.println("3- Kategori Sil");
            System.out.println("4- Araç Oluştur");
            System.out.println("5- Araç Listele");
            System.out.println("6- Araç Sil");
            System.out.println("7- Araç Arama");
            System.out.println("8- Araç Filtreleme(Kategori Bazlı)");
            System.out.println("9- Kiralamaları Listele");
            System.out.println("0- Geri");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();

            switch (choise) {
                case 1:
                    createCategory();
                    break;
                case 2:
                    listCategory();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 4:
                    createVehicle();
                    break;
                case 5:
                    listVehicle();
                case 6:
                    deleteVehicle();
                    break;
                case 7:
                    searchVehicle();
                    break;
                case 8:
                    filterVehicle();
                    break;
                case 9:
                    listRentals();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz İşlem!");
            }


        }


    }

    private static void filterVehicle() {
        System.out.println("Kategori İsmi Giriniz: ");
        System.out.println("(otomobil, motosiklet, helikopter)");
        String categoryName = scanner.next();
        List<Vehicle> vehicles = vehicleService.getAllByCategoryName(categoryName);
        System.out.println("\n-----ARAÇ LİSTESİ (FİLTRELEME SONUCU)-----");
        vehicles.forEach(vehicle ->
                System.out.printf("%s - %s - %s - %s - %s\n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getCategory().getName()));
    }

    private static void searchVehicle() {
        System.out.print("Araç Markası Giriniz: ");
        String searchVehicleName = scanner.next();
        List<Vehicle> vehicles = vehicleService.search(searchVehicleName);
        System.out.println("\n-----ARAÇ LİSTESİ (ARAMA SONUCU)-----");
        vehicles.forEach(vehicle ->
                System.out.printf("%s - %s - %s - %s - %s\n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getCategory().getName()));

    }

    private static void listRentals() {
    }

    private static void deleteVehicle() {
        System.out.print("Silinecek Aracın Id'sini Giriniz: ");
        String vehicleId = scanner.next();
        vehicleService.deleteById(Long.parseLong(vehicleId));

    }

    private static void listVehicle() {
        int totalPage = vehicleService.getTotalPage();
        int page = 1;
        do {
            List<Vehicle> vehicles = vehicleService.getAll(page);
            System.out.println("\n-----Araç Listesi(sayfa : " + page + "/" + totalPage + ")-----");
            vehicles.forEach(vehicle ->
                    System.out.printf("%s - %s - %s - %s - %s\n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getCategory().getName()));

            System.out.print("Sonraki Sayfa Sayısı: ");
            String pageStr = scanner.next();
            page = Integer.parseInt(pageStr);

        }while (page <= totalPage);

    }

    private static void createVehicle() throws AnkaRentalException {
        System.out.println("Araç Markasını Giriniz: ");
        String brand = scanner.next();
        System.out.println("Araç Modelini Giriniz: ");
        String model = scanner.next();
        System.out.println("Araç Yılını Giriniz: ");
        Integer year = scanner.nextInt();
        System.out.println("Araç Kiralama Değerini Giriniz: ");
        String rentalPrice = scanner.next();
        System.out.println("Araç Fiyatını Giriniz: ");
        String price = scanner.next();
        System.out.println("Kategori Id Giriniz: ");
        String categoryId = scanner.next();

        Category category = categoryService.getById(Long.parseLong(categoryId));
        Vehicle vehicle = new Vehicle(brand, model, year, new BigDecimal(rentalPrice) , new BigDecimal(price), category);
        vehicleService.save(vehicle, LOGINED_USER);
    }

    private static void deleteCategory() {
        System.out.println("Kategori Id Giriniz: ");
        String categoryId = scanner.next();
        categoryService.deleteById(Long.parseLong(categoryId));
    }

    private static void listCategory() {
        List<Category> categoryList = categoryService.getAll();
        categoryList.forEach(System.out::println);
    }

    private static void createCategory() throws AnkaRentalException {
        throw new AnkaRentalException("NOT IMPLEMENTED");
    }

    private static void loginCustomer() throws AnkaRentalException {
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        CustomerService customerService = new CustomerService();
        LOGINED_CUSTOMER = customerService.login(email, password);

        while (true){
            System.out.println("1- Araç Listele");
            System.out.println("2- Araç Arama");
            System.out.println("3- Araç Filtreleme(Kategori Bazlı)");
            System.out.println("4- Kiralama Oluştur");
            System.out.println("5- Kiralama Yap");
            System.out.println("6- Geçmiş Kiralamaları Listele");
            System.out.println("0- Geri");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();

            switch (choise){
                case 1:
                    listVehicle();
                    break;
                case 2:
                    searchVehicle();
                    break;
                case 3:
                    filterVehicle();
                    break;
                case 4:
                    createRental();
                    break;
                case 5:
                    makeRental();
                case 6:
                    listPastRentals();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz İşlem!");

            }
        }


    }

    private static void listPastRentals() {
        List<Rent> rents = rentService.getAllByCustomer(LOGINED_CUSTOMER);
        for (Rent rent : rents) {
            System.out.printf("Kiralama #%d - %s",
                    rent.getId(),rent.getRentDate());

            for (RentItem item : rent.getRentItems()){
                System.out.printf(" -> %s - %d", item.getVehicle().getBrand(),
                        item.getPrice());
            }
        }
    }

    private static void makeRental() {

        System.out.println("Bir Ödeme Yöntemi Seçiniz: (CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER)");
        String paymentMethodStr = scanner.next();

        rentService.save(LOGINED_CUSTOMER, PaymentMethod.valueOf(paymentMethodStr));

    }

    private static void createRental() {
        Boolean isVehicleFound = true;
        long id = LOGINED_CUSTOMER.getId();

        while (isVehicleFound) {
            System.out.println("Araç Markası Giriniz: ");
            String brand = scanner.next();

            Vehicle vehicle = vehicleService.getByName(brand);

            if (vehicle == null) {
                System.out.println("Araç Bulunamadı!");
                isVehicleFound = false;
            }else {
                List<CartItem> cart = cartItemService.findByCustomerId(id);
                if (cart == null) {
                    cart = new ArrayList<>();
                }
                cart.add(new CartItem(null,vehicle,new Cart()));
                System.out.println("Kiralama Oluşturuldu!");
                cartService.addToCart(LOGINED_CUSTOMER, vehicle);
            }
        }

    }

    private static void signUpMenu() {
        while (true) {
            System.out.println("-----Kayıt Ol-----");
            System.out.println("1- Müşteri Kayıt");
            System.out.println("2- Kurumsal Müşteri Kayıt");
            System.out.println("3- Kullanıcı Kayıt(ADMİN)");
            System.out.println("0- Geri Dön");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();
            try {
                switch (choise) {
                    case 1:
                        saveCustomer();
                        break;
                    case 2:
                        saveCrprtCustomer();
                        break;
                    case 3:
                        registerUser();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Geçersiz İşlem!");
                }
            } catch (AnkaRentalException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void registerUser() throws AnkaRentalException {
        scanner.nextLine();
        System.out.print("Kullanıcı Adı: ");
        String username = scanner.nextLine();

        System.out.print("Şifre: ");
        String password = scanner.next();

        userService.save(username, password);
    }

    private static void saveCrprtCustomer() {
    }

    private static void saveCustomer() throws AnkaRentalException {
        System.out.print("İsim: ");
        String name = scanner.next();
        System.out.print("Yaş: ");
        int age = scanner.nextInt();
        System.out.println("Lütfen kullanıcı türünü seçin:");
        System.out.println("1 - Bireysel");
        System.out.println("2 - Kurumsal");
        System.out.print("Seçiminiz: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String role;
        switch (choice) {
            case 1:
                role = "individual";
                break;
            case 2:
                role = "corporate";
                break;
            default:
                System.out.println("Geçersiz seçim. Varsayılan olarak BİREYSEL seçildi.");
                role = "individual";
        }
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Şifre: ");
        String password = scanner.next();




        CustomerService customerService = new CustomerService();
        customerService.save(name, age, email, password, role);
    }
}
