package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Model that holds data to represent an address
 */
@Entity
@Table(name="address")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name="address_id")
    private Long addressId;
    @NotNull
    @Column(name="address_one")
    private String addressOne;
    @Column(name="address_two")
    private String addressTwo;
    @Column(name="address_three")
    private String addressThree;
    @NotNull
    @Column(name="city")
    private String city;
    @NotNull
    @Column(name="state")
    private String state;
    @NotNull
    @Column(name="zipcode")
    private String zipcode;
    @Version
    @Column(name="version")
    private int version;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getAddressThree() {
        return addressThree;
    }

    public void setAddressThree(String addressThree) {
        this.addressThree = addressThree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
