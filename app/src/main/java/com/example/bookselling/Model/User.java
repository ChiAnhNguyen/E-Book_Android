package com.example.bookselling.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int customerID;
    private String customerName;
    private String custommerpass;
    private String email;
    private String phone;

    public User() {
    }



    public User(String customerName, String custommerpass, String email, String phone) {
        this.customerName = customerName;
        this.custommerpass = custommerpass;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustommerpass() {
        return custommerpass;
    }

    public void setCustommerpass(String custommerpass) {
        this.custommerpass = custommerpass;
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
        return "User{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", custommerpass='" + custommerpass + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
