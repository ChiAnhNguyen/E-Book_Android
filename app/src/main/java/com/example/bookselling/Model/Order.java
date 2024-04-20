package com.example.bookselling.Model;

public class Order {
    private int orderID;
    private int productID;
    private int custommerID;

    private String customerName;
    private String phonenumb;
    private String diachi;

    public Order() {
    }

    public Order(int productID, int custommerID, String customerName, String phonenumb, String diachi) {
        this.productID = productID;
        this.custommerID = custommerID;

        this.customerName = customerName;
        this.phonenumb = phonenumb;
        this.diachi = diachi;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustommerID() {
        return custommerID;
    }

    public void setCustommerID(int custommerID) {
        this.custommerID = custommerID;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhonenumb() {
        return phonenumb;
    }

    public void setPhonenumb(String phonenumb) {
        this.phonenumb = phonenumb;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", custommerID=" + custommerID +
                ", customerName='" + customerName + '\'' +
                ", phonenumb='" + phonenumb + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }
}
