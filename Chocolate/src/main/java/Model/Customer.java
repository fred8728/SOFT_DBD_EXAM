package Model;

public class Customer {
    private int id;
    private String name;
    private String street;
    private int city_id;
    private String email;
    private String phone;

    public Customer(int id, String name, String street, int city_id, String email, String phone) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city_id = city_id;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city_id=" + city_id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
