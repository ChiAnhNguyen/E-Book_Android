package com.example.bookselling.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class product_detail implements Serializable {
    private String productName,categotyName,author,publisher,descript;
    private double price;
    private int productID;
    private int quantity;


    public product_detail() {
    }

    public product_detail(String productName, String categotyName, String author, String publisher, String descript, double price, int quantity) {
        this.productName = productName;
        this.categotyName = categotyName;
        this.author = author;
        this.publisher = publisher;
        this.descript = descript;
        this.price = price;
        this.quantity=quantity;

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategotyName() {
        return categotyName;
    }

    public void setCategotyName(String categotyName) {
        this.categotyName = categotyName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "product_detail{" +
                "productName='" + productName + '\'' +
                ", categotyName='" + categotyName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", descript='" + descript + '\'' +
                ", price=" + price +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
