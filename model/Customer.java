package Vehicle_rental_app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer extends BaseModel{

    private String name;

    private String email;

    private String password;

    private int age;

    private String role;

    public Customer(){

    }

    public Customer(Long id) {
        this.setId(id);
    }

    public Customer(String name, int age, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
