package jsl.com.library.entities.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Address implements Serializable {
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "zip", nullable = false)
    private String zip;

    public Address() {}

    public Address(String phone, String street, String zip) {
        this.phone = phone;
        this.street = street;
        this.zip = zip;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Address address = (Address) object;
        return Objects.equals(phone, address.phone) && Objects.equals(street, address.street) && Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, street, zip);
    }

    @Override
    public String toString() {
        return "Address{" +
                "phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
