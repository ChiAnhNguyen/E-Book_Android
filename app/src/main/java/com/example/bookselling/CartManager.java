package com.example.bookselling;

import com.example.bookselling.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItemList;

    private CartManager() {
        cartItemList = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProductToCart(Product product) {
        cartItemList.add(product);
    }

    public List<Product> getCartItemList() {
        return cartItemList;
    }

    // Các phương thức khác như removeProduct, clearCart, etc.
}
