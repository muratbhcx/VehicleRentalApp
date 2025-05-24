package Vehicle_rental_app;

import Vehicle_rental_app.exception.AnkaRentalException;
import Vehicle_rental_app.exception.ExceptionMessagesConstans;
import Vehicle_rental_app.model.*;
import Vehicle_rental_app.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class VehicleRentalMain {

    private static User LOGINED_USER;

    private static Customer LOGINED_CUSTOMER;

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();

    private static final CategoryService categoryService = new CategoryService();

    private static final VehicleService vehicleService = new VehicleService();

    private static final RentService rentService = new RentService();


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
            System.out.println("2- Kullanıcı Girişi(ADMİN)");
            System.out.println("0- Geri Dön");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    loginCustomer();
                    break;
                case 2:
                    loginUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz İşlem!");


            }
        }
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
            System.out.println("1- Araç Oluştur");
            System.out.println("2- Araç Listele");
            System.out.println("3- Araç Arama");
            System.out.println("4- Araç Filtreleme(Kategori Bazlı)");
            System.out.println("0- Geri");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();

            switch (choise) {
                case 1:
                    createVehicle();
                    break;
                case 2:
                    listVehicle();
                    break;
                case 3:
                    searchVehicle();
                    break;
                case 4:
                    filterVehicle();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Geçersiz İşlem!");
            }


        }


    }

    private static void filterVehicle() {
        int totalPage = vehicleService.getTotalPage();
        int page = 1;
        System.out.println("Kategori İsmi Giriniz: ");
        System.out.println("(otomobil, motosiklet, helikopter)");
        String categoryName = scanner.next();

        do {
        List<Vehicle> vehicles = vehicleService.getAllByCategoryName(categoryName, page);
        System.out.println("\n-----ARAÇ LİSTESİ (FİLTRELEME SONUCU)(sayfa : " + page + "/" + totalPage + ")-----");
        System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n",
                "MARKA", "MODEL", "ARAÇ YILI", "ARAÇ DEĞERİ", "SAATLİK KİRA FİYATI", "KATEGORİ");
        vehicles.forEach(vehicle ->
                System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRentalPrice(), vehicle.getCategory().getName()));
            System.out.print("Sonraki Sayfa Sayısı: ");
            String pageStr = scanner.next();
            page = Integer.parseInt(pageStr);
        }while (page <= totalPage);
    }

    private static void searchVehicle() {
        System.out.print("Araç Markası Giriniz: ");
        String searchVehicleBrand = scanner.next();
        List<Vehicle> vehicles = vehicleService.search(searchVehicleBrand);
        if (vehicles.isEmpty()) {
            System.out.println("ARAÇ BULUNAMADI!");
            return;
        }
        System.out.println("\n-----ARAÇ LİSTESİ (ARAMA SONUCU)-----");
        System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n",
                "MARKA", "MODEL", "ARAÇ YILI", "ARAÇ DEĞERİ", "SAATLİK KİRA FİYATI", "KATEGORİ");
        vehicles.forEach(vehicle ->
                System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRentalPrice(), vehicle.getCategory().getName()));

    }

    private static void listVehicle() {
        int totalPage = vehicleService.getTotalPage();
        int page = 1;
        do {
            List<Vehicle> vehicles = vehicleService.getAll(page);
            System.out.println("\n-----Araç Listesi(sayfa : " + page + "/" + totalPage + ")-----");
            System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n",
                    "MARKA", "MODEL", "ARAÇ YILI", "ARAÇ DEĞERİ", "SAATLİK KİRA FİYATI", "KATEGORİ");
            vehicles.forEach(vehicle ->
                    System.out.printf("%-10s %-10s %-10s %-15s %-20s %-15s%n", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRentalPrice(), vehicle.getCategory().getName()));

            System.out.print("Sonraki Sayfa Sayısı: ");
            String pageStr = scanner.next();
            page = Integer.parseInt(pageStr);

        } while (page <= totalPage);
    }

    private static void createVehicle() throws AnkaRentalException {
        System.out.println("Araç Markasını Giriniz: ");
        String brand = scanner.next();
        System.out.println("Araç Modelini Giriniz: ");
        String model = scanner.next();
        System.out.println("Araç Yılını Giriniz: ");
        int year = scanner.nextInt();
        System.out.println("Araç Kiralama Değerini Giriniz(SAATLİK): ");
        String rentalPrice = scanner.next();
        System.out.println("Araç Fiyatını Giriniz: ");
        String price = scanner.next();
        System.out.println("Kategori Id Giriniz: ");
        System.out.println("1- otomobil 2- motosiklet 3- helikopter");
        String categoryId = scanner.next();

        Category category = categoryService.getById(Long.parseLong(categoryId));
        Vehicle vehicle = new Vehicle(brand, model, year, new BigDecimal(rentalPrice), new BigDecimal(price), category);
        vehicleService.save(vehicle);
    }

    private static void loginCustomer() throws AnkaRentalException {
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        CustomerService customerService = new CustomerService();
        LOGINED_CUSTOMER = customerService.login(email, password);

        while (true) {
            System.out.println("1- Araç Listele");
            System.out.println("2- Araç Arama");
            System.out.println("3- Araç Filtreleme(Kategori Bazlı)");
            System.out.println("4- Kiralama Yap");
            System.out.println("5- Geçmiş Kiralamaları Listele");
            System.out.println("0- Geri");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();

            switch (choise) {
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
        rentService.listPastRentals(LOGINED_CUSTOMER);

    }

    private static void createRental() {
        boolean isVehicleFound = true;

        while (isVehicleFound) {
            System.out.println("Araç Markası Giriniz: ");
            String brand = scanner.next();
            System.out.println("Araç Modelini Giriniz: ");
            String model = scanner.next();

            Vehicle vehicle = vehicleService.getByBrand(brand, model);

            if (vehicle == null) {
                System.out.println("Araç Bulunamadı!");
                isVehicleFound = false;
            }
            System.out.println("Kiralama tipi seçin (saat/gün/hafta/ay): ");
            String periodType = scanner.next().toLowerCase();
            System.out.print("Kaç " + periodType + " kiralamak istiyorsunuz?: ");
            int time = scanner.nextInt();
            rentService.createRental(LOGINED_CUSTOMER, vehicle, periodType, time);
            return;
        }

    }

    private static void signUpMenu() {
        while (true) {
            System.out.println("-----Kayıt Ol-----");
            System.out.println("1- Müşteri Kayıt");
            System.out.println("2- Kullanıcı Kayıt(ADMİN)");
            System.out.println("0- Geri Dön");
            System.out.println("Seçim Yapınız: ");
            int choise = scanner.nextInt();
            try {
                switch (choise) {
                    case 1:
                        saveCustomer();
                        break;
                    case 2:
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
