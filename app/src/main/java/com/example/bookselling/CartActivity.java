package com.example.bookselling;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Model.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView cart_list;
    private CartAdapter cartAdapter;
    private List<Product> cartProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        cart_list = findViewById(R.id.cart_list);


        // Get the current products in the cart
        cartProductList = CartManager.getInstance().getCartItemList();

        // Check if the cart is not empty
        if(cartProductList != null && !cartProductList.isEmpty()) {
            // Set up the recyclerView with the cart adapter
            cartAdapter = new CartAdapter(this, cartProductList);
            cart_list.setAdapter((ListAdapter) cartAdapter);
            cartAdapter.notifyDataSetChanged();
        } else {
            // Handle empty cart scenario, maybe show a message to the user
            // For example, "Your cart is empty!" or redirect back to product list
        }
    }
}
