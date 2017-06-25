package com.sailgo.model;

import java.io.Serializable;

/**
 * Created by gewa on 6/17/2017.
 */
public class UserModel implements Serializable {

    private int user_id;
    private String firstname;
    private String lastname;
    private String address;
    private String island_city;
    private String state_region;
    private String zip_postcode;
    private String country;
    private String mobile;
    private String email;
    private String username;
    private String password;

    public UserModel() {
    }

    public UserModel(String firstname, String lastname, String address, String island_city, String state_region, String zip_postcode, String country, String mobile, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.island_city = island_city;
        this.state_region = state_region;
        this.zip_postcode = zip_postcode;
        this.country = country;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserModel(int user_id, String firstname, String lastname, String address, String island_city, String state_region, String zip_postcode, String country, String mobile, String email, String username, String password) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.island_city = island_city;
        this.state_region = state_region;
        this.zip_postcode = zip_postcode;
        this.country = country;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsland_city() {
        return island_city;
    }

    public void setIsland_city(String island_city) {
        this.island_city = island_city;
    }

    public String getState_region() {
        return state_region;
    }

    public void setState_region(String state_region) {
        this.state_region = state_region;
    }

    public String getZip_postcode() {
        return zip_postcode;
    }

    public void setZip_postcode(String zip_postcode) {
        this.zip_postcode = zip_postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (user_id != userModel.user_id) return false;
        if (username != null ? !username.equals(userModel.username) : userModel.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", island_city='" + island_city + '\'' +
                ", state_region='" + state_region + '\'' +
                ", zip_postcode='" + zip_postcode + '\'' +
                ", country='" + country + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
