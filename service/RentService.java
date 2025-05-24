package Vehicle_rental_app.service;

import Vehicle_rental_app.dao.RentDAO;
import Vehicle_rental_app.model.*;

import java.math.BigDecimal;
import java.util.List;


public class RentService {

    private final RentDAO rentDAO;


    public RentService() {

        this.rentDAO = new RentDAO();
    }

    public void createRental(Customer loginedCustomer, Vehicle vehicle, String periodType, int time) {
        int age = loginedCustomer.getAge();
        BigDecimal deposit = BigDecimal.ZERO;
        if ("corporate".equals(loginedCustomer.getRole()) && (!periodType.equals("ay") || time < 1)) {
            System.out.println("Kurumsal Müşteriler En Az 1 Aylık Kiralama Yapabilir.");
            return;
        }

        if (vehicle.getPrice().compareTo(BigDecimal.valueOf(2000000)) > 0) {
            if (age < 30) {
                System.out.println("Bu Aracı Sadece 30 Yaşından Büyük Müşteriler Kiralayabilir.");
                return;
            }
            deposit = vehicle.getPrice().multiply(BigDecimal.valueOf(0.10));
            System.out.println("Bu Araç İçin Gereken Depozito: " + deposit + " TL");

        }
        BigDecimal amount = vehicle.getRentalPrice();
        BigDecimal totalAmount = BigDecimal.ZERO;
        long totalHours;


        switch (periodType) {
            case "saat":
                totalHours = time;
                totalAmount = amount.multiply(BigDecimal.valueOf(totalHours));
                break;
            case "gün":
                totalHours = time * 24;
                totalAmount = amount.multiply(BigDecimal.valueOf(totalHours));
                break;
            case "hafta":
                totalHours = time * 24 * 7;
                totalAmount = amount.multiply(BigDecimal.valueOf(totalHours));
                break;
            case "ay":
                totalHours = time * 24 * 30;
                totalAmount = amount.multiply(BigDecimal.valueOf(totalHours));
                break;
            default:
                System.out.println("GEÇERSİZ İŞLEM!");
        }
        BigDecimal finalAmount = totalAmount.add(deposit);
        System.out.println("TOPLAM: " +  finalAmount + " TL");

        Rent rent = new Rent();
        rent.setCustomer(new Customer(loginedCustomer.getId()));
        rent.setVehicleBrand(vehicle.getBrand());
        rent.setVehicleModel(vehicle.getModel());
        rent.setTotalAmount(finalAmount);

        rentDAO.save(rent);
        System.out.println("Kiralama Yapıldı!");


    }


    public void listPastRentals(Customer loginedCustomer) {
        List<Rent> rents = rentDAO.findAllByCustomerId(loginedCustomer.getId());
        if (rents.isEmpty()) {
            System.out.println("GEÇMİŞ KİRALAMA BULUNAMADI!\n");
            return;
        }
        System.out.println("\n-----GEÇMİŞ KİRALAMALAR-----");
        System.out.printf("%-10s %-10s %-10s%n", "MARKA", "MODEL", "TUTAR");
        for (Rent rent : rents) {
            System.out.printf("%-10s %-10s %-10sTL%n\n",
                    rent.getVehicleBrand(),
                    rent.getVehicleModel(),
                    rent.getTotalAmount());
        }
    }
}
