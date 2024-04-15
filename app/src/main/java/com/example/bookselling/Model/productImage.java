package com.example.bookselling.Model;

import java.util.Arrays;

public class productImage {
    int imageID;
    int productID;
    private byte[] imageData;

    public productImage() {
    }

    public productImage(int imageID, int productID, byte[] imageData) {
        this.imageID = imageID;
        this.productID = productID;
        this.imageData = imageData;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "productImage{" +
                "imageID=" + imageID +
                ", productID=" + productID +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
