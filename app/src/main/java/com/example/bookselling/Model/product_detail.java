package com.example.bookselling.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class product_detail implements Serializable {
    String productName,categotyName,author,publisher,descript;
    double price;
    int productID;


    public product_detail() {
    }

    public product_detail(String productName, String categotyName, String author, String publisher, String descript, double price) {
        this.productName = productName;
        this.categotyName = categotyName;
        this.author = author;
        this.publisher = publisher;
        this.descript = descript;
        this.price = price;

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
                '}';
    }
}
