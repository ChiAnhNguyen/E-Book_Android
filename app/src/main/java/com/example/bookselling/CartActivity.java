package com.example.bookselling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Database.Db_Cart;
import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.EventBus.TinhTongEvent;
import com.example.bookselling.Model.Product;
import com.example.bookselling.Model.User;
import com.example.bookselling.MyAdapter.CartAdapter;
import com.example.bookselling.Service.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextView giohangtrong,tongtien;
    private Toolbar toolbar;
    private ListView list_cart;
    private Button btnMuahang;
    private  CartAdapter adapter;
    private Db_Cart dbCart;
    private User user;
    private double tongtienSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        initView();
        getUserPreferences();
        initControl();
        tinhTongTien();
    }

    private void tinhTongTien() {
        tongtienSP = 0;
        for(int i = 0; i<Utils.arr_Cart.size(); i++){
            tongtienSP = tongtienSP + Utils.arr_Cart.get(i).getPrice()* Utils.arr_Cart.get(i).getQuantity();
        }
        tongtien.setText(tongtienSP+"$");
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utils.arr_Cart = getCartbyID(user.getCustomerID());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(Utils.arr_Cart.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else{
            adapter = new CartAdapter(this,Utils.arr_Cart,user);
            list_cart.setAdapter(adapter);

        }
        btnMuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mauhang = new Intent(getApplicationContext(),CheckoutActivity.class);
                mauhang.putExtra("tongtien",tongtienSP);
                startActivity(mauhang);
            }
        });
    }

    private void initView() {
        giohangtrong = findViewById(R.id.emtyCart);
        tongtien = findViewById(R.id.txttontien);
        toolbar = findViewById(R.id.toolbarInCart);
        list_cart = findViewById(R.id.listcart);
        btnMuahang = findViewById(R.id.btnCheckout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void enventTinhTongTien(TinhTongEvent event){
        if(event!=null){
            tinhTongTien();
        }
    }
    public List<Cart> getCartbyID(int userID){
        List<Cart>cart = new ArrayList<>();
        dbCart = new Db_Cart(getApplicationContext());
        cart= dbCart.getCartByUserID(userID);
        return cart;
    }
    public User getUserPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userString= sharedPreferences.getString("user",null);
        user = gson.fromJson(userString,User.class);
        return user;
    }
}
